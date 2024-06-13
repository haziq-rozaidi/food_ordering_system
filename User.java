public abstract class User{
    private String userID;
    private String userName;
    private String password;

    public void login(int type, String userName, String password) throws Exception{
        if(type == 1){
        }

        else if (type == 2){
        }
        
        this.userName = userName;
        this.password = password;
    }

    public abstract void register();
}