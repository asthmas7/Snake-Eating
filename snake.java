import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.abs;

public class snake {
    public static void findRoad(String dir,HashMap<String,LinkedList<int[][]>> snakeMap,String map[][],int x,int y){
        int flag =x*y;                                              //初始化为指定值
        int min=x*y;

        for(int i=0;i<snakeMap.get("x").size();i++){                                                // 判断距离蛇头最近的蛇身是否相邻，并记录相应存储id
            if(snakeMap.get(">").get(0)[0][0]-snakeMap.get("x").get(i)[0][0]==0){
                if(min>abs(snakeMap.get(">").get(0)[0][1]-snakeMap.get("x").get(i)[0][1])){
                    min = abs(snakeMap.get(">").get(0)[0][1]-snakeMap.get("x").get(i)[0][1]);
                    flag=i;
                }

            }
        }
        for(int i=0;i<snakeMap.get("x").size();i++){
            if(snakeMap.get(">").get(0)[0][1]-snakeMap.get("x").get(i)[0][1]==0){
                if(min>abs(snakeMap.get(">").get(0)[0][0]-snakeMap.get("x").get(i)[0][0])){
                    min = abs(snakeMap.get(">").get(0)[0][0]-snakeMap.get("x").get(i)[0][0]);
                    flag=i;
                }

            }
        }
        if(flag==x*y){                                                              //蛇头蛇身不相邻
            System.out.println("map信息有误，构不成蛇身");
            return;
        }


        String locaH=null;                                                          //位置记录变量
        String locaX=null;
        int xTail,xFront,xHead;
        int yTail,yFront,yHead;
        xTail=xFront=xHead=0;
        yTail=yFront=yHead=0;

        switch (dir){
            case "w":{
                //判断
                if(snakeMap.get(">").get(0)[0][0] == 0) {                                                   // 蛇头在第一行无法向上
                    System.out.println("蛇前进方向遇到边界，无法移动");
                   break;
                }
                if(snakeMap.get(">").get(0)[0][0] > snakeMap.get("x").get(flag)[0][0]){                    //蛇头蛇身反方向
                    System.out.println("蛇头前进方向为相反方向，蛇无法运动");
                    break;
                }
                if(map[snakeMap.get(">").get(0)[0][0]-1][snakeMap.get(">").get(0)[0][1]].equals("1")){    //蛇头上方为1 障碍物
                    System.out.println("蛇头前进方向为障碍物");
                    break;
                }
                else{
                    locaH = judge(snakeMap.get(">").get(0),snakeMap.get("x").get(flag));                  //蛇头在蛇身相对位置
                    locaX = judge(snakeMap.get("x").get(flag),snakeMap.get("x").get(xOther(flag)));      //蛇身在蛇尾相对位置


                    xTail = snakeMap.get("x").get(flag)[0][0];                                              //记录蛇头蛇身蛇尾坐标
                    yTail = snakeMap.get("x").get(flag)[0][1];
                    xFront = snakeMap.get(">").get(0)[0][0];
                    yFront = snakeMap.get(">").get(0)[0][1];
                    xHead = snakeMap.get(">").get(0)[0][0]-1;
                    yHead = snakeMap.get(">").get(0)[0][1];

                    map[snakeMap.get("x").get(xOther(flag))[0][0]][snakeMap.get("x").get(xOther(flag))[0][1]]="0";      //移动后，蛇尾位置 置0

                    snakeMap.get(">").get(0)[0][0]--;                                                   //蛇头位置更新
                    update(snakeMap.get("x").get(flag),locaH);                                          //蛇身位置更新
                    update(snakeMap.get("x").get(xOther(flag)),locaX);                                  //蛇尾位置更新

                    map[xTail][yTail]="x";                                                               //更新蛇在map中的展示
                    map[xFront][yFront]="x";
                    map[xHead][yHead]=">";

                    break;
                }
            }
            case "a":{
                if(snakeMap.get(">").get(0)[0][1] == 0) {                                                // 蛇头在最左行无法向左
                    System.out.println("蛇前进方向遇到边界，无法移动");
                    break;
                }
                if(snakeMap.get(">").get(0)[0][1] > snakeMap.get("x").get(flag)[0][1]){                    //蛇头蛇身反方向
                    System.out.println("蛇头前进方向为相反方向，蛇无法运动");
                    break;
                }
                if(map[snakeMap.get(">").get(0)[0][0]][snakeMap.get(">").get(0)[0][1]-1].equals("1")){    //蛇头左方为1 障碍物
                    System.out.println("蛇头前进方向为障碍物");
                    break;
                }
                else{
                    locaH = judge(snakeMap.get(">").get(0),snakeMap.get("x").get(flag));
                    locaX = judge(snakeMap.get("x").get(flag),snakeMap.get("x").get(xOther(flag)));

                    xTail = snakeMap.get("x").get(flag)[0][0];
                    yTail = snakeMap.get("x").get(flag)[0][1];
                    xFront = snakeMap.get(">").get(0)[0][0];
                    yFront = snakeMap.get(">").get(0)[0][1];
                    xHead = snakeMap.get(">").get(0)[0][0];
                    yHead = snakeMap.get(">").get(0)[0][1]-1;

                    map[snakeMap.get("x").get(xOther(flag))[0][0]][snakeMap.get("x").get(xOther(flag))[0][1]]="0";
                    snakeMap.get(">").get(0)[0][1]--;
                    update(snakeMap.get("x").get(flag),locaH);
                    update(snakeMap.get("x").get(xOther(flag)),locaX);

                    map[xTail][yTail]="x";
                    map[xFront][yFront]="x";
                    map[xHead][yHead]=">";
                    break;
                }
            }
            case "s":{
                if(snakeMap.get(">").get(0)[0][0] == x-1) {                                         // 蛇头在最下行无法向下
                    System.out.println("蛇前进方向遇到边界，无法移动");
                    break;
                }
                if(snakeMap.get(">").get(0)[0][0] < snakeMap.get("x").get(flag)[0][0]){             //蛇头蛇身反方向
                    System.out.println("蛇头前进方向为相反方向，蛇无法运动");
                    break;
                }
                if(map[snakeMap.get(">").get(0)[0][0]+1][snakeMap.get(">").get(0)[0][1]].equals("1")){    //蛇头下方为1 障碍物
                    System.out.println("蛇头前进方向为障碍物");
                    break;
                }
                else{
                    locaH = judge(snakeMap.get(">").get(0),snakeMap.get("x").get(flag));
                    locaX = judge(snakeMap.get("x").get(flag),snakeMap.get("x").get(xOther(flag)));

                    xTail = snakeMap.get("x").get(flag)[0][0];
                    yTail = snakeMap.get("x").get(flag)[0][1];
                    xFront = snakeMap.get(">").get(0)[0][0];
                    yFront = snakeMap.get(">").get(0)[0][1];
                    xHead = snakeMap.get(">").get(0)[0][0]+1;
                    yHead = snakeMap.get(">").get(0)[0][1];

                    map[snakeMap.get("x").get(xOther(flag))[0][0]][snakeMap.get("x").get(xOther(flag))[0][1]]="0";

                    snakeMap.get(">").get(0)[0][0]++;
                    update(snakeMap.get("x").get(flag),locaH);
                    update(snakeMap.get("x").get(xOther(flag)),locaX);

                    map[xTail][yTail]="x";
                    map[xFront][yFront]="x";
                    map[xHead][yHead]=">";
                    break;
                }

            }
            case "d":{
                if(snakeMap.get(">").get(0)[0][1] == y-1) {           // 蛇头在最右行无法向右
                    System.out.println("蛇前进方向遇到边界，无法移动");
                    break;
                }
                if(snakeMap.get(">").get(0)[0][1] < snakeMap.get("x").get(flag)[0][1]){    //蛇头蛇身反方向
                    System.out.println("蛇头前进方向为相反方向，蛇无法运动");
                    break;
                }
                if(map[snakeMap.get(">").get(0)[0][0]][snakeMap.get(">").get(0)[0][1]+1].equals("1")){    //蛇头右方为1 障碍物
                    System.out.println("蛇头前进方向为障碍物");
                    break;
                }
                else{
                    locaH = judge(snakeMap.get(">").get(0),snakeMap.get("x").get(flag));
                    locaX = judge(snakeMap.get("x").get(flag),snakeMap.get("x").get(xOther(flag)));

                    xTail = snakeMap.get("x").get(flag)[0][0];
                    yTail = snakeMap.get("x").get(flag)[0][1];
                    xFront = snakeMap.get(">").get(0)[0][0];
                    yFront = snakeMap.get(">").get(0)[0][1];
                    xHead = snakeMap.get(">").get(0)[0][0];
                    yHead = snakeMap.get(">").get(0)[0][1]+1;

                    map[snakeMap.get("x").get(xOther(flag))[0][0]][snakeMap.get("x").get(xOther(flag))[0][1]]="0";
                    snakeMap.get(">").get(0)[0][1]++;
                    update(snakeMap.get("x").get(flag),locaH);
                    update(snakeMap.get("x").get(xOther(flag)),locaX);

                    map[xTail][yTail]="x";
                    map[xFront][yFront]="x";
                    map[xHead][yHead]=">";
                    break;
                }

            }
            default:{
                System.out.println("输入方向字符有误，请重新输入！");
                break;
            }
        }
        System.out.println("xTail: "+snakeMap.get("x").get(0)[0][0]+" , "+"yTail: "+snakeMap.get("x").get(0)[0][1]+" , "+"xFront: "+snakeMap.get("x").get(1)[0][0]+" , "+"yFront: "+snakeMap.get("x").get(1)[0][1]+" , "+"xHead: "+snakeMap.get(">").get(0)[0][0]+" , "+"yHead: "+snakeMap.get(">").get(0)[0][1]);
    }

    public static String judge(int[][] a,int[][]b){                                    //判断相对位置
        if(a[0][0]-b[0][0]==0){
            if(a[0][1]-b[0][1]>0){
                return "right";
            }else
                return "left";
        }else if(a[0][1]-b[0][1]==0){
            if(a[0][0]-b[0][0]>0){
                return "down";
            }else
                return "up";
        }else
            return "error";
    }
    public static void update(int[][]b,String locaH){                           //移动后，更新坐标

        switch (locaH){
            case "right":{
                b[0][1]++;break;
            }
            case "left":{
                b[0][1]--;break;
            }
            case "down":{
                b[0][0]++;break;
            }
            case "up":{
                b[0][0]--;break;
            }
        }
    }

    public static int xOther(int flag){                                         // 计算另一蛇尾，在XLinkedList的位置
        if(flag == 0)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) {
        System.out.println("snake game start");
        System.out.println("");

        File file = new File("map.txt");                                // 读取map文件
        try {
            FileInputStream fileInputStream = new FileInputStream(file);           //文件流读取
            int size = fileInputStream.available();
//            System.out.println(size);
            List list = new ArrayList();
            for(int i=0; i< size ;i++){
                int asc = fileInputStream.read();
                if(asc !=32 && asc !=10 && asc != -1){
                    list.add((char)asc);
                }
            }
            fileInputStream.close();
//            System.out.println(list);
            int x = Integer.parseInt(list.get(0).toString());                       //读取文件第一行地图画布长宽
            int y = Integer.parseInt(list.get(1).toString());
            String map[][] = new String[x][y];                                      //创建map二维数组

            list.remove(0);                                                    //移除画布长宽数据，map二维数组仅存地图数据
            list.remove(0);
            int index=0;

            int[][] snakeHCor = new int[1][2];                                          //创建蛇头坐标数组，用1*2 数组存取（X,Y）

            LinkedList<int[][]> XlinkedList = new LinkedList<>();                       //采用LinkedList存储多组蛇身坐标
            LinkedList<int[][]> HlinkedList = new LinkedList<>();
            HashMap<String,LinkedList<int[][]>> snakeMap = new HashMap<String,LinkedList<int[][]>>();

            for(int i=0;i<x;i++){                                                       //遍历map，查找蛇头蛇身蛇尾坐标
                for(int j=0;j<y;j++,index++){
                    map[i][j]= list.get(index).toString();

                    if(map[i][j].equals("x")){
                        int[][] snakeXCor = new int[1][2];                              //创建蛇身蛇尾坐标数组，用1*2数组存取（X，Y）
                        snakeXCor[0][0] = i;
                        snakeXCor[0][1] = j;
                        XlinkedList.add(snakeXCor);
                        snakeMap.put("x",XlinkedList);                                  //使用<String,LinkedList>解决存储 相同key值，不同value
                    }
                    if(map[i][j].equals(">")){                                          //记录蛇头坐标
                        snakeHCor[0][0] = i;
                        snakeHCor[0][1] = j;
                        HlinkedList.add(snakeHCor);
                        snakeMap.put(">",HlinkedList);
                    }

                }
            }
//            System.out.println(snakeMap);
            System.out.println("map 信息");
            for(int i=0;i<x;i++){                                                       //输出map信息
                for(int j=0;j<y;j++){
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }

            if(snakeMap.get("x").get(0)[0][0] == snakeMap.get("x").get(1)[0][0]){                       //判断蛇身蛇尾是否相邻，否则构不成蛇,不进行游戏
                if( abs(snakeMap.get("x").get(0)[0][1]-snakeMap.get("x").get(1)[0][1])> 1){
                    System.out.println("蛇身蛇尾不相连");
                    return;
                }
            }else if(snakeMap.get("x").get(0)[0][1] == snakeMap.get("x").get(1)[0][1]){
                if( abs(snakeMap.get("x").get(0)[1][0]-snakeMap.get("x").get(1)[1][0])> 1){
                    System.out.println("蛇身蛇尾不相连");
                    return;
                }
            }

            Scanner in = new Scanner(System.in);
            System.out.println("");
            System.out.println("请输入'w','s','a','d' 进行测试");
            while (in.hasNextLine()){
                String dir = in.nextLine();                                             //读取方向字符，大小写敏感
                findRoad(dir,snakeMap,map,x,y);                                          //game主函数
                System.out.println("map 信息");
                for(int i=0;i<x;i++){                                                     //输出map信息
                    for(int j=0;j<y;j++){
                        System.out.print(map[i][j]);
                    }
                    System.out.println();
                }
            }


        } catch (FileNotFoundException e) {                                             //读取文件失败，异常抛出
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
