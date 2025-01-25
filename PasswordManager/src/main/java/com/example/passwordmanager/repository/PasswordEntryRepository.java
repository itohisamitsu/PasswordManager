package com.example.passwordmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.passwordmanager.entity.PasswordEntry;

public interface PasswordEntryRepository extends JpaRepository<PasswordEntry, Long> {}