package com.nwjon.sdata;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jondann on 7/29/16.
 */
public class Application {

    public static void main(String[] args) {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        BookRepository repository = context.getBean(BookRepository.class);

        //oneBook(repository);
        //allBooks(repository);
        //allCertainBooks(repository);
        //saveMultiple(repository);
        saveSingle(repository);
    }

    private static void oneBook(BookRepository repository){
        Book book = repository.findOne(1L);
        System.out.println(book.toString());
    }

    private static void allBooks(BookRepository repository){
        List<Book> books = repository.findAll();
        for (Book book: books){
            System.out.println(book.toString());
        }
    }

    private static void allCertainBooks(BookRepository repository){

        List<Book> books = repository.findAll(new ArrayList<Long>(){{
            add(1L);
            add(3L);
            add(7L);
        }});
        for (Book book: books){
            System.out.println(book.toString());
        }
    }

    private static void saveMultiple(BookRepository repository){
        List<Book> books = BookUtil.create(5);
        repository.save(books);
    }

    private static void saveSingle(BookRepository repository){

        Book book = new Book();
        book.setTitle("Such a good book");
        book.setPrice(new BigDecimal("15.85"));
        book.setPublishDate(new Date());
        book.setPageCount(235);

        repository.save(book);
    }
}
