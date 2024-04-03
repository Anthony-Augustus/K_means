package first_homework;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Better_Ramdom {
	 // 创建一个Random对象用于生成随机数 
	Random random = new Random();
	// 创建一个DecimalFormat对象用于格式化输出  
	DecimalFormat df = new DecimalFormat("#.00");
	
	 /**  
     * 生成一个长度为n的double类型数组，数组中的元素为0到1之间的随机小数  
     * @param n 数组的长度  
     * @return 包含随机小数的double类型数组  
     */  
	public double[] array_random(int n) {
		double a[] = new double[n];
		for(int i=1;i<=n;i++) {
			double random_number = random.nextInt(101)/100.0;
			a[i-1] = random_number;
		}
		return a;
	}
	
	/**  
     * 生成一个长度为size的double类型数组，数组中的元素为m到n之间的随机小数  
     * @param m 随机数范围的下界（包含）  
     * @param n 随机数范围的上界（不包含）  
     * @param size 数组的长度  
     * @return 包含随机小数的double类型数组  
     */  
	public double[] full_random_array(int m, int n, int size) {
		double a[] = new double[size];
		for(int i=0;i<=size-1;i++) {
			a[i] = (random.nextInt((n-m)*100)+ m*100)/100.0;
		}
		return a;
	}
	
	/**  
     * 生成一个在m到n之间的随机小数  
     * @param m 随机数范围的下界（包含）  
     * @param n 随机数范围的上界（不包含）  
     * @return 生成的随机小数  
     */  
	public double full_random(int m, int n) {
		double a = (random.nextInt((n-m)*100)+m*100)/100.0;
		return a;
	}
	
	/**  
     * 生成一个长度为size的double类型数组，数组中的元素满足以下条件：  
     * 1. 数组中的每个元素在m到n之间  
     * 2. 数组中所有元素的和接近但不超过给定的restrict值  
     * @param m 随机数范围的下界（包含）  
     * @param n 随机数范围的上界（不包含）  
     * @param size 数组的长度  
     * @param restrict 数组元素和的限制值  
     * @return 生成的double类型数组  
     */  
	public double[] random_add_restrict(int m, int n, int size, double restrict) {
		double a;
		double xy[] = new double[size];
		if(size != 1) {
			a = this.full_random(m, n);
			xy[0] = a;
			double sum = a;
			int i = 1;
			int j = 1;
			do {
				a = full_random(m, n);
				xy[i] = a;
				i++;
				sum = sum + a;
			}while(i<size-1);
			double sumcopy = sum;
			int mn = 1;
			if ((sum>=0&&restrict<=0)||(sum<0&&restrict>0)) {
				mn = -1;
				sum = mn*sum;
			}
			if(sum>=restrict+(n-m)/2) {
				while(sum>=restrict+(n-m)/2) {
					sum = sum/2;
					j = j*2;
					}
			}
			if(sum<=restrict-(n-m)/2) {
				while(sum<=restrict-(n-m)/2) {
					sum = sum*2;
					j = j*2;
				}
			}
			if (sumcopy>=restrict+(n-m)/2) {
				for (int l = 0;l<size-1;l++) {
					xy[l] = xy[l]/j*mn;// 根据缩放因子j和符号mn调整数组元素的值
				}
			}
			if (sumcopy<=restrict-(n-m)/2) {
				for (int l = 0;l<size-1;l++) {
					xy[l] = xy[l]*j*mn;// 根据缩放因子j和符号mn调整数组元素的值
					}
			}
			double abc = restrict - sum;
			xy[size-1] = abc;// 将剩余值赋给数组的最后一个元素 
			xy[size-1] = Double.valueOf(df.format(abc));// 格式化最后一个元素的小数点位数  
		}else {
			xy[0] = 1;// 如果数组长度为1，直接赋值为1
		}
		return xy;// 返回生成的数组  
	}
	
	/**  
     * 创建一个有在一个正整数以内的整数范围的三维点数组  
     * @param m 坐标x随机数范围（不包含）  
     * @param n 坐标y随机数范围（不包含）
     * @param o 坐标z随机数范围  
     * @param size 数组的长度  
     * @return 生成的double类型数组  
     */  
	public double[][] random_tdenmension(int m, int n, int o, int size) {
		// 确保m、n和o都是正数  
		if (m <= 0 || n <= 0 || o <= 0) {  
            throw new IllegalArgumentException("m, n, and o must be positive integers.");  
        }  
		// 创建一个二维数组a，其大小为size行和3列  
		double[][] a = new double[size][3];
		for(int i = 0;i<size;i++) {
			a[i][0] = random.nextInt(m*100)/100.0;
			a[i][1] = random.nextInt(n*100)/100.0;
			a[i][2] = random.nextInt(o*100)/100.0;
		}
		return a;
	}
}