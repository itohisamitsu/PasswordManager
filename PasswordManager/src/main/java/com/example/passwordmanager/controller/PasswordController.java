package com.example.passwordmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.passwordmanager.entity.PasswordEntry;
import com.example.passwordmanager.repository.PasswordEntryRepository;

@RestController
@RequestMapping("/passwords")
public class PasswordController {

	@Autowired
	private PasswordEntryRepository passwordEntryRepository;
	
	@PostMapping
	public PasswordEntry createPassword(@RequestBody PasswordEntry passwordEntry) {
		return passwordEntryRepository.save(passwordEntry);
	}
	
	@GetMapping
	public List<PasswordEntry> readAllPasswords() {
		return passwordEntryRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public PasswordEntry readPasswordById(@PathVariable Long id) {
		return passwordEntryRepository.findById(id).orElseThrow();
	}
	
	@PutMapping("/{id}")
	public PasswordEntry updatePassword(@PathVariable Long id, @RequestBody PasswordEntry passwordEntry) {
		return passwordEntryRepository.findById(id).map(existingEntry -> {
			existingEntry.setAppName(passwordEntry.getAppName());
			existingEntry.setUsername(passwordEntry.getUsername());
			existingEntry.setPassword(passwordEntry.getPassword());
			return passwordEntryRepository.save(existingEntry);
		}).orElseThrow(() -> new RuntimeException("パスワードが存在しません：" + id));
	}
	
	@DeleteMapping("/{id}")
	public void deletePassword(@PathVariable Long id) {
		passwordEntryRepository.deleteById(id);
	}
}