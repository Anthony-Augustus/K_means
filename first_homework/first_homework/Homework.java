package first_homework;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Homework {

	public static void main(String[] args) {
		// ����һ��Better_Ramdom����������������� 
		Better_Ramdom rn = new Better_Ramdom();
		// ����һ��Scanner�������ڴӿ���̨��ȡ����  
		Scanner sc = new Scanner(System.in);
		 // ����һ��K_means����
		K_means k = new K_means();
		// ����һ��Random�����������������
		Random r_n = new Random();
		// ����һ��DecimalFormat�������ڸ�ʽ�������������λС��  
		DecimalFormat df = new DecimalFormat("#0.00");
		//����10��[0,1]֮��������������С�������λ��������������ĺͲ������
		double ab[] = rn.full_random_array(0, 1, 10);
		double sum = 0;
		for(int i=0;i<=10-1;i++) {
			double random_number = ab[i];
			sum = sum + random_number;
		}

		System.out.println("1.ʮ��0~1֮���������ĺ�Ϊ��"+df.format(sum));
		
		
		//����n��[0,1]֮��������������С�������λ�����Ӵ�С���������
		int n = r_n.nextInt(1, 10);// ����1��9֮������������Ϊn��ֵ 
		System.out.println("\n������Ŀn��ֵΪ��"+n);
		double abc[] = rn.full_random_array(0, 1, n);
		Arrays.sort(abc);
		
	    //�����ַ��������Ż�,�������滻����λ����
        int len = abc.length;
        int mid = len >> 1;
        //����һ���µĵ�������
        double[] newArray = new double[len];
        for (int i = 0; i <= mid; i++) {
            newArray[i] = abc[len - 1 - i];
	            newArray[len - 1 - i] = abc[i];
        }
        
		System.out.println("\n2.[0,1]��n����������Ӵ�С���(nΪ���������)��");
		for(int i=0;i<n;i++) {
			double a = newArray[i];
			System.out.println(a);
		}
		
		
		/*
		 * [i, j]֮��������������С�������λ��������������ĺͲ����
		 * , �Ӵ�С��������� ����i�� j�Ӽ������롣
		 */
		System.out.println("\n3.������һ������������ķ�Χ��");
		int input2 = sc.nextInt(); // ���뷶Χi 
		int input3 = sc.nextInt(); // ���뷶Χj  
		double abcd[] = rn.full_random_array(input2, input3, n);
		Arrays.sort(abcd);
		double sumOfRange = 0;
		for(int i=0;i<=n-1;i++) {
			double a = abcd[i];  
            System.out.println(a); // ��������  
            sumOfRange += a; // ����������ĺ�  
		}
		System.out.println("n��������ĺ�Ϊ: " + df.format(sumOfRange)); // ���������ĺ�  
		
		//����n��[-1,1]֮��������������С�������λ����Ҫ������������ĺ�Ϊ1���������
		System.out.println("\n4.����[-1,1]֮��������,��������ĺ�Ϊ1��");
		double abcde[] = rn.random_add_restrict(-1, 1, n,1);
		for(int i=0;i<n;i++) {
			double a = abcde[i];
			System.out.println(a);
		}
		 // �ر�Scanner����  
		sc.close();
		//����n��[a,b,c]֮��������������С�������λ��
	    //�����ֳ����ࣨ����k��ֵ�㷨kmeans�����ֱ������
		System.out.println("\n5.��������n��[a,b,c]֮��������������ŷʽ���ε�kmeans��Ϊ���࣬����������ʵ�Ϊ��");
		// ���� rn �� random_tdenmension �����������ɶ�ά�������������������8����ά������� 
		double[][] data = rn.random_tdenmension(1, 2, 3, 8);
		
		// ���� k �� generate_controid �������ڳ�ʼ��k�����������ʵ㣬�����ʼ����2����ά�������ʵ� 
		double[][] controid = k.generate_controid(1, 2, 3, 2);
		
		// ���� k �� iteration ��������ִ��k��ֵ�㷨�ĵ������̣��������յ���������ʵ� 
		double controid_xyz[][] = k.iteration(controid, data,1000);
		
		// ������յ���������ʵ�
		for(int i = 0;i<controid_xyz.length;i++) {
			System.out.println("�ʵ�:("+df.format(controid_xyz[i][0])+","+df.format(controid_xyz[i][1])+","+df.format(controid_xyz[i][2])+")");
		}
		
		// ��ȡkmeans�����Ľ��������k��cluster��һ����ά���飬�洢ÿ���صĵ� 
		double cluster[][][] = k.cluster;
		
		// ����ÿ����������ʵ㣬��������Ӧ�Ĵ��еĵ�  
		for(int i = 0;i<2;i++) {
			System.out.println("�������ʵ�("+df.format(controid_xyz[i][0])+","+df.format(controid_xyz[i][1])+","+df.format(controid_xyz[i][2])+")�Ĵأ�");
			
			 // ����k.a��k.b�ֱ��ʾ�������Ĵ��е������ 
			if(i==0) {
				//��Ϊ��ǰ��k_means������ͨ�����������ʵ���k.aΪ0������Ѿ��ų���һ����ֳ����������ض������㣬�����ܳ��ֿգ����Դ��¿��Բ����жϡ�
				if(k.a!=0) {
					for(int j = 0;j<k.a;j++) {
						System.out.println("("+cluster[i][j][0]+","+cluster[i][j][1]+","+cluster[i][j][2]+")");
					}
				}else if (k.a==0) {
					System.out.println("��");
				}
			}
			if(i==1) {
				//ͬ�ϣ���ǰk.bΪ0������Ѿ��ų���һ����ֳ����࣬�����ܳ��֣������жϡ������������Ϊ"��"��
				if(k.b!=0) {
					for(int j = 0;j<k.b;j++) {
						System.out.println("("+cluster[i][j][0]+","+cluster[i][j][1]+","+cluster[i][j][2]+")");
					}
				}else if (k.b==0) {
					System.out.println("��");
				}
			}
			}
		}
}
