package nichol.springframework.spring5webapp.bootstrap;

import nichol.springframework.spring5webapp.model.Author;
import nichol.springframework.spring5webapp.model.Book;
import nichol.springframework.spring5webapp.model.Publisher;
import nichol.springframework.spring5webapp.repositories.AuthorRepository;
import nichol.springframework.spring5webapp.repositories.BookRepository;
import nichol.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;

	private BookRepository bookRepository;

	private PublisherRepository publisherRepository;

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}

	private void initData() {
		// Uhtred
		Author uhtred = new Author("Uhtred", "Ragnarson");
		Publisher publisherNichol = new Publisher("Nichol", "Red queen avenue");
		Book theLastKingdom = new Book("The Last Kingdom", "123456", publisherNichol);
		uhtred.getBooks().add(theLastKingdom);
		theLastKingdom.getAuthors().add(uhtred);
		authorRepository.save(uhtred);
		publisherRepository.save(publisherNichol);
		bookRepository.save(theLastKingdom);

		// Brida
		Author brida = new Author("Brida", "Lala");
		Publisher publisherRagnar = new Publisher("Ragnar", "Blue queen avenue");
		Book vikings = new Book("Vikings", "222222", publisherRagnar);
		brida.getBooks().add(vikings);
		vikings.getAuthors().add(brida);
		authorRepository.save(brida);
		publisherRepository.save(publisherRagnar);
		bookRepository.save(vikings);
	}
}
