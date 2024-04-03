package first_homework;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Better_Ramdom {
	 // ����һ��Random����������������� 
	Random random = new Random();
	// ����һ��DecimalFormat�������ڸ�ʽ�����  
	DecimalFormat df = new DecimalFormat("#.00");
	
	 /**  
     * ����һ������Ϊn��double�������飬�����е�Ԫ��Ϊ0��1֮������С��  
     * @param n ����ĳ���  
     * @return �������С����double��������  
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
     * ����һ������Ϊsize��double�������飬�����е�Ԫ��Ϊm��n֮������С��  
     * @param m �������Χ���½磨������  
     * @param n �������Χ���Ͻ磨��������  
     * @param size ����ĳ���  
     * @return �������С����double��������  
     */  
	public double[] full_random_array(int m, int n, int size) {
		double a[] = new double[size];
		for(int i=0;i<=size-1;i++) {
			a[i] = (random.nextInt((n-m)*100)+ m*100)/100.0;
		}
		return a;
	}
	
	/**  
     * ����һ����m��n֮������С��  
     * @param m �������Χ���½磨������  
     * @param n �������Χ���Ͻ磨��������  
     * @return ���ɵ����С��  
     */  
	public double full_random(int m, int n) {
		double a = (random.nextInt((n-m)*100)+m*100)/100.0;
		return a;
	}
	
	/**  
     * ����һ������Ϊsize��double�������飬�����е�Ԫ����������������  
     * 1. �����е�ÿ��Ԫ����m��n֮��  
     * 2. ����������Ԫ�صĺͽӽ���������������restrictֵ  
     * @param m �������Χ���½磨������  
     * @param n �������Χ���Ͻ磨��������  
     * @param size ����ĳ���  
     * @param restrict ����Ԫ�غ͵�����ֵ  
     * @return ���ɵ�double��������  
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
					xy[l] = xy[l]/j*mn;// ������������j�ͷ���mn��������Ԫ�ص�ֵ
				}
			}
			if (sumcopy<=restrict-(n-m)/2) {
				for (int l = 0;l<size-1;l++) {
					xy[l] = xy[l]*j*mn;// ������������j�ͷ���mn��������Ԫ�ص�ֵ
					}
			}
			double abc = restrict - sum;
			xy[size-1] = abc;// ��ʣ��ֵ������������һ��Ԫ�� 
			xy[size-1] = Double.valueOf(df.format(abc));// ��ʽ�����һ��Ԫ�ص�С����λ��  
		}else {
			xy[0] = 1;// ������鳤��Ϊ1��ֱ�Ӹ�ֵΪ1
		}
		return xy;// �������ɵ�����  
	}
	
	/**  
     * ����һ������һ�����������ڵ�������Χ����ά������  
     * @param m ����x�������Χ����������  
     * @param n ����y�������Χ����������
     * @param o ����z�������Χ  
     * @param size ����ĳ���  
     * @return ���ɵ�double��������  
     */  
	public double[][] random_tdenmension(int m, int n, int o, int size) {
		// ȷ��m��n��o��������  
		if (m <= 0 || n <= 0 || o <= 0) {  
            throw new IllegalArgumentException("m, n, and o must be positive integers.");  
        }  
		// ����һ����ά����a�����СΪsize�к�3��  
		double[][] a = new double[size][3];
		for(int i = 0;i<size;i++) {
			a[i][0] = random.nextInt(m*100)/100.0;
			a[i][1] = random.nextInt(n*100)/100.0;
			a[i][2] = random.nextInt(o*100)/100.0;
		}
		return a;
	}
}