package first_homework;

public class K_means {
	// Better_Ramdom类，用于生成随机数  
	Better_Ramdom rn = new Better_Ramdom();
	// 初始化两个整数变量a和b，用于记录两个簇的点数 
	public int a = 0;
	public int b = 0;
	// 三维数组，用于存储最终的聚类结果 
	public double cluster[][][];
	
	/**  
	 * 生成指定数量的质心  
	 * @param contoid_x 质心x坐标的范围  
	 * @param controid_y 质心y坐标的范围  
	 * @param controid_z 质心z坐标的范围  
	 * @param controid_number 质心的数量  
	 * @return 返回生成的质心数组  
	 */  
	public double[][] generate_controid(int contoid_x, int controid_y, int controid_z ,int controid_number) {
		// 调用Better_Ramdom类的random_tdenmension方法生成质心  
		double contoird[][] = rn.random_tdenmension(contoid_x,controid_y,controid_z,controid_number);
		// 返回生成的质心数组 
		return contoird;
	}
	
	/**  
	 * 计算每个数据点与质心之间的欧氏距离  
	 * @param data 数据点数组  
	 * @param controid 质心数组  
	 * @return 返回每个数据点与每个质心之间的距离数组  
	 */  
	public double[][] oushi_distance(double data[][], double controid[][]) {	
		// 数据点的数量 
		int data_num = data.length;
		// 初始化一个二维数组，用于存储数据点与质心之间的距离
		double distances_cd[][] = new double[data_num][2];
		double distance;
		// 遍历每个数据点  
		for(int i=0;i<=data_num-1;i++) {
			// 遍历每个质心 
			for(int j=0;j<2;j++) {
			// 计算当前数据点与第j个质心之间的欧氏距离 
			distance = Math.sqrt(Math.pow((data[i][0] - controid[j][0]),2)+
								Math.pow((data[i][1] - controid[j][1]),2)+
								Math.pow((data[i][2] - controid[j][2]),2));
			// 将计算出的距离存储到distances_cd数组中 
			distances_cd[i][j] = distance;
			}
		}
		// 返回存储了距离信息的数组 
		return distances_cd;
	}
	
	/**  
	 * 根据数据点与质心之间的距离，将数据点分配给最近的质心  
	 * @param distances_cd 数据点与质心之间的距离数组  
	 * @param data 数据点数组  
	 * @return 返回按质心分配的数据点数组  
	 */  
	public double[][][] point_assign(double distances_cd[][],double data[][]) {
		// 重置a和b的值为0，因为我们要重新分配数据点到质心 
		a = 0;
		b = 0;
		// 初始化两个二维数组，用于临时存储分配给不同质心的数据点 
		double k1[][] = new double[data.length][3];
		double k2[][] = new double[data.length][3];
		// 遍历每个数据点  
		for(int i=0;i<data.length;i++) {
			// 如果当前数据点到第一个质心的距离小于到第二个质心的距离
			if(distances_cd[i][0]>distances_cd[i][1]) {
				// 将数据点添加到第一个质心的数组中 
				k1[a][0] = data[i][0];
				k1[a][1] = data[i][1];
				k1[a][2] = data[i][2];
				a++;// 计数器加1  
			}
			// 如果当前数据点到第二个质心的距离小于或等于到
			if(distances_cd[i][0]<=distances_cd[i][1]) {
				// 将数据点添加到第二个质心的数组中
				k2[b][0] = data[i][0];
				k2[b][1] = data[i][1];
				k2[b][2] = data[i][2];
				b++;// 计数器加1  
			}
		}
		// 初始化最终的三维数组，用于存储按质心分配的数据点
		double k_zhi[][][] = new double[2][a+b][3];
		// 将第一个质心数组中的数据点复制到最终的三维数组中
		for (int i = 0; i < k1.length ; i++) {
			k_zhi[0][i][0] = k1[i][0];
			k_zhi[0][i][1] = k1[i][1];
			k_zhi[0][i][2] = k1[i][2];
		}
		// 将第二个质心数组中的数据点复制到最终的三维数组中
		for (int i = 0; i < k2.length ; i++) {
			k_zhi[1][i][0] = k2[i][0];
			k_zhi[1][i][1] = k2[i][1];
			k_zhi[1][i][2] = k2[i][2];
		}
		// 将最终的三维数组赋值给类的成员变量cluster
		cluster = k_zhi;
		// 返回按质心分配的数据点数组
		return k_zhi; 
	}
	
	/**  
	 * 根据数据点数组 `k_zhi` 计算新的质心位置。  
	 * 如果某个质心没有分配到任何数据点，则随机生成一个质心位置。  
	 * @param k_zhi 三维数组，存储按质心分配的数据点  
	 * @return 新的质心位置组成的二维数组  
	 */
	public double[][] find_controid(double k_zhi[][][]) {
		// 创建一个新的二维数组来存储新的质心位置  
		double new_centroid[][] = new double[k_zhi.length][3]; 
		double sum1 = 0;
		double sum2 = 0;
		double sum3 = 0;
		// 遍历每个质心  
		for(int i = 0;i<=k_zhi.length-1;i++) {
			// 初始化三个坐标轴的和为0 
			sum1 = 0;
			sum2 = 0;
			sum3 = 0;
			// 遍历当前质心下的所有数据点  
			for(int j = 0;j<k_zhi[i].length;j++) {
				// 将数据点的三个坐标轴的值分别累加到对应的和中  
				sum1 = sum1+k_zhi[i][j][0];
				sum2 = sum2+k_zhi[i][j][1];
				sum3 = sum3+k_zhi[i][j][2];
			}
			
			// 根据当前质心的数据点数量来计算新的质心位置  
			if(i==0) {
				// 如果是第一个质心 
				if(a!=0) {
					// 如果第一个质心有数据点
					new_centroid[i][0] = sum1/a;// 计算新的x坐标 
					new_centroid[i][1] = sum2/a;// 计算新的y坐标  
					new_centroid[i][2] = sum3/a;// 计算新的z坐标 
				}else if (a==0) {
					// 如果第一个质心没有数据点，则随机生成一个新的质心位置  
					double single_centroid[][] = rn.random_tdenmension(1, 2, 3, 1); 
					new_centroid[i][0] = single_centroid[0][0];// 设置随机生成的x坐标
					new_centroid[i][1] = single_centroid[0][1];// 设置随机生成的y坐标 
					new_centroid[i][2] = single_centroid[0][2];// 设置随机生成的z坐标
				}
			}
			
			if(i==1) {
				// 如果是第二个质心，同理计算  
				if(b!=0) {
					new_centroid[i][0] = sum1/b;
					new_centroid[i][1] = sum2/b;
					new_centroid[i][2] = sum3/b;
				}else if (b==0) {
					double single_centroid[][] = rn.random_tdenmension(1, 2, 3, 1);
					new_centroid[i][0] = single_centroid[0][0];
					new_centroid[i][1] = single_centroid[0][1];
					new_centroid[i][2] = single_centroid[0][2];
				}
			}
		}
		// 返回新的质心数组 
		return new_centroid;	
	}
	
	/**  
	 * 计算所有数据点到其所属质心的距离的平方和（SSE）。  
	 * @param distances 二维数组，存储每个数据点到两个质心的距离  
	 * @return SSE值，即所有数据点到其所属质心的距离的平方和  
	 */
	//取得SSE。
	public double need_iteration(double distances[][]) {
		double SSE=0;
			for(int i = 0;i<distances.length;i++) {
				for(int j = 0; j<2;j++) {
					SSE = SSE + distances[i][j];
				}
		}
		return SSE;
	}
	
	/**  
	 * 执行K-means聚类算法的迭代过程，直至算法收敛或达到最大迭代次数。  
	 *  
	 * @param initial_centroids 初始质心位置的二维数组  
	 * @param data 数据点的二维数组  
	 * @param convergenceThreshold 收敛阈值，用于判断算法是否收敛  
	 * @return 最终的质心位置组成的二维数组  
	 */
	public double[][] iteration(double initial_centroids[][], double data[][], double convergenceThreshold) {  
	    double current_centroids[][] = initial_centroids;  
	    double previous_SSE = Double.MAX_VALUE; // 初始化一个非常大的SSE值  
	    double current_SSE;  
	    boolean hasConverged = false;  
	  
	    while (!hasConverged) {  
	        double distances[][] = this.oushi_distance(data, current_centroids);  
	        double k_zhi[][][] = this.point_assign(distances, data);  
	        double new_centroids[][] = this.find_controid(k_zhi);  
	        current_SSE = this.need_iteration(distances);  
	        
	          
	        // 检查收敛条件：如果SSE的变化小于阈值，则认为算法已经收敛  
	        if (Math.abs(previous_SSE - current_SSE) < convergenceThreshold) {  
	            hasConverged = true;  
	        } else {  
	            previous_SSE = current_SSE;  
	            current_centroids = new_centroids; // 更新质心位置以进行下一次迭代 
	            
	        }  
	    }
	      
	    // 打印最终的SSE或执行其他后续操作  
	    //System.out.println("Final SSE: " + current_SSE);
	    // 如果需要，返回最终的质心位置  
	     return current_centroids;  
	}
}
	
