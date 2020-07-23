package com.cimb.bikelahuserdb.controller;

import java.util.List;
import java.util.Optional;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.bikelahuserdb.dao.UserRepo;
import com.cimb.bikelahuserdb.entity.ItemListModel;
import com.cimb.bikelahuserdb.entity.TransactionModel;
import com.cimb.bikelahuserdb.entity.UsersModel;
import com.cimb.bikelahuserdb.entity.Users;
import com.cimb.bikelahuserdb.util.EmailUtil;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailUtil emailUtil;

	/*Get semua user data*/
	@GetMapping()
	public Iterable<Users> getAllUsers() {
		return userRepo.findAll();
	}

	/*Get user data dengan username*/
	@GetMapping("/username/{username}")
	public Optional<Users> getByUsername(@PathVariable() String username) {
		return userRepo.findByUsername(username);
	}

	/*Get user data dengan email*/
	@GetMapping("/email/{email}")
	public Optional<Users> getByEmail(@PathVariable() String email) {
		return userRepo.findByEmail(email);
	}

	/*Hapus user data*/
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable() int id) {
		userRepo.deleteById(id);
	}

	/*Daftar user baru*/
	@PostMapping
	public Users registerUser(@RequestBody Users user) {
		return userRepo.save(user);
	}
	
	/*Send verification email*/
	@PostMapping("/verify/{email}")
	public String sendVerificationEmail(@PathVariable String email) {
		String linkVerify = "Silahkan klik link di bawah ini untuk verifikasi email\n"
				+ " http://localhost:8080/users/verify/"+email;
		this.emailUtil.sendEmail(email, "Verifkasi email toko BIKELAH", linkVerify);
		return "email sent!";
	}

	/*Verify email user*/
	@GetMapping("/verify/{email}")
	public String verifyByEmail(@PathVariable String email) {
		System.out.println("Email sudah diverifikasi");
		Users findUserEmail = userRepo.findByEmail(email).get();
		if (findUserEmail == null) {
			throw new RuntimeException("User not found");
		}

		findUserEmail.setVerified(true);
		;
		userRepo.save(findUserEmail);
		return "email " + email + " berhasil diverfikasi";
	}

	/*Ganti password user*/
	@PatchMapping("/key/{key1}/{key2}")
	public String changePassword(@PathVariable int key1, @PathVariable String key2,
			@RequestBody UsersModel userData) {
		Users finduser = userRepo.findById(key1).get();

		System.out.print(finduser.getPassword());
		if (finduser.getPassword().equals(key2)) {
			System.out.print("Berhasil");
			finduser.setPassword(userData.getPassword());
		}
		userRepo.save(finduser);
		return "halo";
	}

	/*Mengirim email lupa password*/
	@PostMapping("/sendemailreset/{email}")
	public String sendEmailReset(@PathVariable() String email) {
		Users finduser = userRepo.findByEmail(email).get();
		String linkVerify = "Silahkan klik link di bawah ini untuk mereset password"
				+ " http://localhost:3000/resetpassword/" + finduser.getId() + "/" + finduser.getPassword();
		this.emailUtil.sendEmail(email, "Reset email toko BIKELAH", linkVerify);
		return "email sent!";
	}

	/*Mengirim invoice*/
	@PostMapping("/transaction")
	public void sendInvoice(@RequestBody TransactionModel transaction) {
		System.out.println(transaction.getId());
		System.out.println(transaction.getTotalPayment());
		List<ItemListModel> itemList = transaction.getItemList();
		for (ItemListModel println : itemList) {
			System.out.println(println.getProductName());
		}

		Users finduser = userRepo.findById(transaction.getUser()).get();

		/*Membuat format currency */
		DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

		formatRp.setCurrencySymbol("Rp. ");
		formatRp.setMonetaryDecimalSeparator(',');
		formatRp.setGroupingSeparator('.');

		kursIndonesia.setDecimalFormatSymbols(formatRp);

		String linkVerify = "Berikut bukti transaksi yang telah dilakukan.\nId transaksi : " + transaction.getId()+"\n";
		int i = 1;
		for (ItemListModel println : itemList) {
			linkVerify += i + ". " + println.getProductName() + "\t dengan harga" + kursIndonesia.format(println.getPrice()) + "\t Sebanyak"
					+ println.getQuantity() + "buah. \n";
			i++;
		}
		linkVerify += "\nTotal transaksi adalah " + kursIndonesia.format(transaction.getTotalPayment())
				+ "\nTerima kasih.";
		this.emailUtil.sendEmail(finduser.getEmail(), "Invoice transaksi BIKELAH", linkVerify);
	}

	/*controller untuk merubah data pengguna */
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateInfo(@Validated @PathVariable int id, @RequestBody UsersModel userdata) {
		Optional<Users> finduser = userRepo.findById(id);
		if (!finduser.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Users user = finduser.get();
		if (userdata.getUsername() != null) {
			user.setUsername(userdata.getUsername());
		}

		if (userdata.getPassword() != null) {
			user.setPassword(userdata.getPassword());
		}

		if (userdata.getFullname() != null) {
			user.setFullname(userdata.getFullname());
		}
		
		if (userdata.getEmail() != null) {
			if (!userdata.getEmail().equals(user.getEmail())) {
				user.setVerified(false);
			}
			user.setEmail(userdata.getEmail());
		}
		
		if (userdata.getAddress() != null) {
			user.setAddress(userdata.getAddress());
		}
		if (userdata.getPhone() != null) {
			user.setPhone(userdata.getPhone());
		}
		if (userdata.getLastlogin() != null) {
			user.setLastlogin(userdata.getLastlogin());
		}

		userRepo.save(user);

		return ResponseEntity.noContent().build();
	}

}