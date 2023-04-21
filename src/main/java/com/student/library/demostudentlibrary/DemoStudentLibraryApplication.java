package com.student.library.demostudentlibrary;

import com.student.library.demostudentlibrary.model.*;
import com.student.library.demostudentlibrary.repository.AuthorRepository;
import com.student.library.demostudentlibrary.repository.BookRepository;
import com.student.library.demostudentlibrary.repository.CardRepository;
import com.student.library.demostudentlibrary.repository.StudentRepository;
import com.student.library.demostudentlibrary.security.User;
import com.student.library.demostudentlibrary.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoStudentLibraryApplication implements CommandLineRunner {
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CardRepository cardRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoStudentLibraryApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//		User admin = User.builder()
//				.userName("ADMIN")
//				.password(encoder.encode("Admin123"))
//				.authority("ADMIN")
//				.emailId("admin@gmail.com")
//				.build();
//
//		User student = User.builder()
//				.userName("Rahim")
//				.password(encoder.encode("Rahim123"))
//				.authority("STUDENT")
//				.emailId("rahim@gmail.com")
//				.build();
//		userRepository.saveAll(Arrays.asList(admin, student));

//		Card card = new Card();
//		card.setCardStatus(CardStatus.ACTIVATED);
//		Student student = new Student("Arvind", 30, "USA", "arvind@gmail.com");
//		card.setStudent(student);
//		student.setCard(card);
//		cardRepository.save(card);
//
//		Author author = new Author("Hemamalini", 30, "India", "hemamalini@gmal.com");
//		Book book = new Book("Hypothesis of Science", Genre.NON_FICTIONAL, true, author ); //author is set here already.
//		List<Book> bookList = new ArrayList<>();
//		bookList.add(book);
//		author.setBooksList(bookList);
//		authorRepository.save(author);
//		bookRepository.save(book);
	}
}
