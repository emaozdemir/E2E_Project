package pojos.BookStorePojos;

public class UsernamePassword{
    private String password;
    private String userName;

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    @Override
    public String toString(){
        return
                "UsernamePassword{" +
                        "password = '" + password + '\'' +
                        ",userName = '" + userName + '\'' +
                        "}";
    }
}