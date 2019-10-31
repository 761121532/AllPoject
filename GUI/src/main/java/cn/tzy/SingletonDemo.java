package cn.tzy;

//创建一个单例模式的对象  用来保存   上一个窗口的参数
public class SingletonDemo {
    //类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static SingletonDemo instance;

    private String url;

    private int action;

    private int end;

    //构造器私有化
    private SingletonDemo(String url,int action,int end){
        this.action=action;
        this.end=end;
        this.url=url;
    }

    //方法同步，调用效率低
    public static synchronized SingletonDemo getInstance(String url,int action,int end){
        if(instance==null){
            instance=new SingletonDemo(url,action,end);
        }
        return instance;
    }
}
