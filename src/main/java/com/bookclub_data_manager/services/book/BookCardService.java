package com.bookclub_data_manager.services.book;

import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.Genre;
import com.bookclub_data_manager.repository.BookCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookCardService {

    @Autowired
    BookCardRepository bookCardRepository;

    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;
    @Autowired
    GenreService genreService;

    public List<Integer> getAuthorsIdByBook(int book_id){
        return bookCardRepository.getAuthorsIdByBook(book_id);
    }
    public List<Integer> getGenresIdByBook(int book_id){
        return bookCardRepository.getGenresIdByBook(book_id);
    }

    public String setBookAndUserDependencies(Integer book_id, Integer user_id){
        if (book_id == null || user_id == null){
            return "Нет идентификатора книги или пользователя";
        }
        bookCardRepository.setBookAndUser(book_id, user_id);
        return "OK";
    }

    public String unsetBookAndUserDependencies(Integer book_id, Integer user_id){
        if (book_id == null || user_id == null){
            return "Нет идентификатора книги или пользователя";
        }
        bookCardRepository.unsetBookAndUser(book_id, user_id);
        return "OK";
    }

    public String setAuthorAndUserDependencies(Integer author_id, Integer user_id){
        if (author_id == null || user_id == null){
            return "Нет идентификатора автора или пользователя";
        }
        bookCardRepository.setAuthorAndUser(author_id, user_id);
        return "OK";
    }

    public String unsetAuthorAndUserDependencies(Integer author_id, Integer user_id){
        if (author_id == null || user_id == null){
            return "Нет идентификатора автора или пользователя";
        }
        bookCardRepository.unsetAuthorAndUser(author_id, user_id);
        return "OK";
    }

    public String setGenreAndUserDependencies(Integer genre_id, Integer user_id){
        if (genre_id == null || user_id == null){
            return "Нет идентификатора автора или пользователя";
        }
        bookCardRepository.setGenreAndUser(genre_id, user_id);
        return "OK";
    }

    public String unsetGenreAndUserDependencies(Integer genre_id, Integer user_id){
        if (genre_id == null || user_id == null){
            return "Нет идентификатора автора или пользователя";
        }
        bookCardRepository.unsetGenreAndUser(genre_id, user_id);
        return "OK";
    }

    public String setBookCardDependencies(Integer bookId, List<String> authors, List<String> genres){
        if (authors.isEmpty()){
            return "Список авторов пустой";
        }
        for (String author : authors) {
            if (authorService.getIdByName(author) == null){
                authorService.add(author);
            }
            bookCardRepository.setBookAndAuthor(bookId, authorService.getIdByName(author));
        }
        if(!genres.isEmpty()){
            for (String genre : genres) {
                if(genreService.getIdByName(genre) == null){
                    genreService.add(genre);
                }
                bookCardRepository.setBookAndGenre(bookId, genreService.getIdByName(genre));
            }
        }
        return "OK";
    }

    public String unsetBookCardDependencies(Integer bookId){
        bookCardRepository.unsetBookAndAuthor(bookId);
        bookCardRepository.unsetBookAndAuthor(bookId);
        return "OK";
    }

    public String addOnlyBookNameAndAuthor(String name, List<String> authors){
        if (name == null || authors.isEmpty()){
            return "Имя и автор не могут быть пустыми";
        }
        bookService.add(name, -1, -1.0F, -1.0F);
        authorService.addList(authors);
        setBookCardDependencies(bookService.getIdByName(name), authors, new ArrayList<>());
        return "OK";
    }

    public String updateOnlyBookNameAndAuthor(int book_id, String name, List<String> authors){
        if (name == null || authors.isEmpty()){
            return "Имя и автор не могут быть пустыми";
        }
        bookService.update(name, -1, -1.0F, -1.0F, book_id);
        authorService.updateList(authors, getAuthorsIdByBook(book_id));
        return "OK";
    }

    public String addBookAuthorGenre(String name, Integer pages, Float litres_rating, Float live_lib_rating, List<String> authors, List<String> genres){
        if (name == null || authors.isEmpty()){
            return "Имя и автор не могут быть пустыми";
        }
        bookService.add(name, pages, litres_rating, live_lib_rating);
        authorService.addList(authors);
        if (!genres.isEmpty()){
            genreService.addList(genres);
        }
        setBookCardDependencies(bookService.getIdByName(name), authors, genres);
        return "OK";
    }

    public String deleteBookById(Integer bookId){
        if(bookService.doesBookExists(bookId).isEmpty()){
            return "Книги c таким идентификатором нет в списке";
        }
        bookService.deleteById(bookId);
        unsetBookCardDependencies(bookId);
        return "OK";
    }

    public String updateBookAuthorGenre(int bookId, String name, Integer pages, Float litres_rating, Float live_lib_rating, List<String> authors, List<String> genres){
        if (name == null || authors.isEmpty()){
            return "Имя и автор не могут быть пустыми";
        }
        bookService.update(name, pages, litres_rating, live_lib_rating, bookId);
        List<Integer> authors_ids = getAuthorsIdByBook(bookId);
        if (authors_ids.isEmpty()){
            return "Ошибка";
        }

        authorService.updateList(authors, authors_ids);
        if (!genres.isEmpty()){
            List<Integer> genre_ids = getGenresIdByBook(bookId);
            if (genre_ids.isEmpty()){
                return "Ошибка";
            }
            genreService.updateList(genres, genre_ids);
        }
        return "OK";
    }

}
