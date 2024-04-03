package first_homework;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Homework {

	public static void main(String[] args) {
		// 创建一个Better_Ramdom对象，用于生成随机数 
		Better_Ramdom rn = new Better_Ramdom();
		// 创建一个Scanner对象，用于从控制台读取输入  
		Scanner sc = new Scanner(System.in);
		 // 创建一个K_means对象
		K_means k = new K_means();
		// 创建一个Random对象，用于生成随机数
		Random r_n = new Random();
		// 创建一个DecimalFormat对象，用于格式化输出，保留两位小数  
		DecimalFormat df = new DecimalFormat("#0.00");
		//生成10个[0,1]之间的随机数（保留小数点后两位），计算随机数的和并输出；
		double ab[] = rn.full_random_array(0, 1, 10);
		double sum = 0;
		for(int i=0;i<=10-1;i++) {
			double random_number = ab[i];
			sum = sum + random_number;
		}

		System.out.println("1.十个0~1之间的随机数的和为："+df.format(sum));
		
		
		//生成n个[0,1]之间的随机数（保留小数点后两位），从大到小排序并输出；
		int n = r_n.nextInt(1, 10);// 生成1到9之间的随机整数作为n的值 
		System.out.println("\n以下题目n的值为："+n);
		double abc[] = rn.full_random_array(0, 1, n);
		Arrays.sort(abc);
		
	    //倒置字符串数组优化,将除法替换成移位操作
        int len = abc.length;
        int mid = len >> 1;
        //定义一个新的倒置数组
        double[] newArray = new double[len];
        for (int i = 0; i <= mid; i++) {
            newArray[i] = abc[len - 1 - i];
	            newArray[len - 1 - i] = abc[i];
        }
        
		System.out.println("\n2.[0,1]的n个随机数，从大到小输出(n为随机数生成)：");
		for(int i=0;i<n;i++) {
			double a = newArray[i];
			System.out.println(a);
		}
		
		
		/*
		 * [i, j]之间的随机数（保留小数点后两位），计算随机数的和并输出
		 * , 从大到小排序并输出； 其中i和 j从键盘输入。
		 */
		System.out.println("\n3.请输入一个随机数产生的范围：");
		int input2 = sc.nextInt(); // 输入范围i 
		int input3 = sc.nextInt(); // 输入范围j  
		double abcd[] = rn.full_random_array(input2, input3, n);
		Arrays.sort(abcd);
		double sumOfRange = 0;
		for(int i=0;i<=n-1;i++) {
			double a = abcd[i];  
            System.out.println(a); // 输出随机数  
            sumOfRange += a; // 计算随机数的和  
		}
		System.out.println("n个随机数的和为: " + df.format(sumOfRange)); // 输出随机数的和  
		
		//生成n个[-1,1]之间的随机数（保留小数点后两位），要求所有随机数的和为1，并输出。
		System.out.println("\n4.生成[-1,1]之间的随机数,且随机数的和为1：");
		double abcde[] = rn.random_add_restrict(-1, 1, n,1);
		for(int i=0;i<n;i++) {
			double a = abcde[i];
			System.out.println(a);
		}
		 // 关闭Scanner对象  
		sc.close();
		//生成n个[a,b,c]之间的随机数（保留小数点后两位）
	    //，划分成两类（采用k均值算法kmeans），分别输出。
		System.out.println("\n5.以下生成n个[a,b,c]之间的随机数，利用欧式几何的kmeans分为两类，两类的中心质点为：");
		// 假设 rn 的 random_tdenmension 方法用于生成多维的随机数，这里生成了8个三维的随机数 
		double[][] data = rn.random_tdenmension(1, 2, 3, 8);
		
		// 假设 k 的 generate_controid 方法用于初始化k个类别的中心质点，这里初始化了2个三维的中心质点 
		double[][] controid = k.generate_controid(1, 2, 3, 2);
		
		// 假设 k 的 iteration 方法用于执行k均值算法的迭代过程，返回最终的类别中心质点 
		double controid_xyz[][] = k.iteration(controid, data,1000);
		
		// 输出最终的类别中心质点
		for(int i = 0;i<controid_xyz.length;i++) {
			System.out.println("质点:("+df.format(controid_xyz[i][0])+","+df.format(controid_xyz[i][1])+","+df.format(controid_xyz[i][2])+")");
		}
		
		// 获取kmeans聚类后的结果，假设k的cluster是一个三维数组，存储每个簇的点 
		double cluster[][][] = k.cluster;
		
		// 遍历每个类别中心质点，并输出其对应的簇中的点  
		for(int i = 0;i<2;i++) {
			System.out.println("以下是质点("+df.format(controid_xyz[i][0])+","+df.format(controid_xyz[i][1])+","+df.format(controid_xyz[i][2])+")的簇：");
			
			 // 假设k.a和k.b分别表示两个类别的簇中点的数量 
			if(i==0) {
				//因为此前在k_means方法中通过重新生成质点让k.a为0的情况已经排除，一定会分成两簇且两簇都包含点，不可能出现空，所以此下可以不用判断。
				if(k.a!=0) {
					for(int j = 0;j<k.a;j++) {
						System.out.println("("+cluster[i][j][0]+","+cluster[i][j][1]+","+cluster[i][j][2]+")");
					}
				}else if (k.a==0) {
					System.out.println("无");
				}
			}
			if(i==1) {
				//同上，此前k.b为0的情况已经排除，一定会分成两类，不可能出现，不用判断。即不可能输出为"无"。
				if(k.b!=0) {
					for(int j = 0;j<k.b;j++) {
						System.out.println("("+cluster[i][j][0]+","+cluster[i][j][1]+","+cluster[i][j][2]+")");
					}
				}else if (k.b==0) {
					System.out.println("无");
				}
			}
			}
		}
}
