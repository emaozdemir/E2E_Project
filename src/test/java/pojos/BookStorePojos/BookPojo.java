package pojos.BookStorePojos;

import java.util.List;

public class BookPojo{

	private List<CollectionOfIsbnsItem> collectionOfIsbns;
	private String userId;

	public void setCollectionOfIsbns(List<CollectionOfIsbnsItem> collectionOfIsbns){
		this.collectionOfIsbns = collectionOfIsbns;
	}

	public List<CollectionOfIsbnsItem> getCollectionOfIsbns(){
		return collectionOfIsbns;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"BookPojo{" + 
			"collectionOfIsbns = '" + collectionOfIsbns + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}