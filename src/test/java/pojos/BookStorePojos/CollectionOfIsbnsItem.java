package pojos.BookStorePojos;

public class CollectionOfIsbnsItem{

	private String isbn;

	public void setIsbn(String isbn){
		this.isbn = isbn;
	}

	public String getIsbn(){
		return isbn;
	}

	@Override
 	public String toString(){
		return 
			"CollectionOfIsbnsItem{" + 
			"isbn = '" + isbn + '\'' + 
			"}";
		}
}
