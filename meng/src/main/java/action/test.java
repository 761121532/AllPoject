package action;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class test {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\优化新方法带联系方式.txt");
        try {
           List<String> list= FileUtils.readLines(file,"utf-8");
           int[] numbers=new int[list.size()];
           HashMap<Integer , String> map = new HashMap<Integer , String>();
           int p=0;
            for (String s : list){
                var a=s.split(" ");
                int number=Integer.parseInt(a[0]);
                numbers[p]=number;
                map.put(number,a[4]);
                p++;
            }
            //表示n次排序过程。
//            for(int  i=0; i<numbers.length; i++){
//                for(int j=1; j<numbers.length-i; j++){
//                    //前面的数字大于后面的数字就交换
//                    if(numbers[j-1] > numbers[j]){
//                        //交换a[j-1]和a[j]
//                        int temp;
//                        temp = numbers[j-1];
//                        numbers[j-1] = numbers[j];
//                        numbers[j]=temp;
//                    }
//                }
//            }
            Arrays.sort(numbers);
            for (var i=0 ; i<numbers.length;i++){
                if (i<numbers.length-1){
                    if (numbers[i]+1!=numbers[i+1]){
                        System.out.println(numbers[i]);
                    }
                }
            }
            for (int i=0;i<numbers.length;i++){
                for (Map.Entry<Integer, String> Entry : map.entrySet()) {
                    if (Entry.getKey()==numbers[i]){
                        FileUtils.write(new File("C:\\Users\\Administrator\\Desktop\\优化排序好了的.txt"),Entry.getValue()+"\n","utf-8",true);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}