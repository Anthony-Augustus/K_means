package first_homework;

public class K_means {
	// Better_Ramdom�࣬�������������  
	Better_Ramdom rn = new Better_Ramdom();
	// ��ʼ��������������a��b�����ڼ�¼�����صĵ��� 
	public int a = 0;
	public int b = 0;
	// ��ά���飬���ڴ洢���յľ����� 
	public double cluster[][][];
	
	/**  
	 * ����ָ������������  
	 * @param contoid_x ����x����ķ�Χ  
	 * @param controid_y ����y����ķ�Χ  
	 * @param controid_z ����z����ķ�Χ  
	 * @param controid_number ���ĵ�����  
	 * @return �������ɵ���������  
	 */  
	public double[][] generate_controid(int contoid_x, int controid_y, int controid_z ,int controid_number) {
		// ����Better_Ramdom���random_tdenmension������������  
		double contoird[][] = rn.random_tdenmension(contoid_x,controid_y,controid_z,controid_number);
		// �������ɵ��������� 
		return contoird;
	}
	
	/**  
	 * ����ÿ�����ݵ�������֮���ŷ�Ͼ���  
	 * @param data ���ݵ�����  
	 * @param controid ��������  
	 * @return ����ÿ�����ݵ���ÿ������֮��ľ�������  
	 */  
	public double[][] oushi_distance(double data[][], double controid[][]) {	
		// ���ݵ������ 
		int data_num = data.length;
		// ��ʼ��һ����ά���飬���ڴ洢���ݵ�������֮��ľ���
		double distances_cd[][] = new double[data_num][2];
		double distance;
		// ����ÿ�����ݵ�  
		for(int i=0;i<=data_num-1;i++) {
			// ����ÿ������ 
			for(int j=0;j<2;j++) {
			// ���㵱ǰ���ݵ����j������֮���ŷ�Ͼ��� 
			distance = Math.sqrt(Math.pow((data[i][0] - controid[j][0]),2)+
								Math.pow((data[i][1] - controid[j][1]),2)+
								Math.pow((data[i][2] - controid[j][2]),2));
			// ��������ľ���洢��distances_cd������ 
			distances_cd[i][j] = distance;
			}
		}
		// ���ش洢�˾�����Ϣ������ 
		return distances_cd;
	}
	
	/**  
	 * �������ݵ�������֮��ľ��룬�����ݵ��������������  
	 * @param distances_cd ���ݵ�������֮��ľ�������  
	 * @param data ���ݵ�����  
	 * @return ���ذ����ķ�������ݵ�����  
	 */  
	public double[][][] point_assign(double distances_cd[][],double data[][]) {
		// ����a��b��ֵΪ0����Ϊ����Ҫ���·������ݵ㵽���� 
		a = 0;
		b = 0;
		// ��ʼ��������ά���飬������ʱ�洢�������ͬ���ĵ����ݵ� 
		double k1[][] = new double[data.length][3];
		double k2[][] = new double[data.length][3];
		// ����ÿ�����ݵ�  
		for(int i=0;i<data.length;i++) {
			// �����ǰ���ݵ㵽��һ�����ĵľ���С�ڵ��ڶ������ĵľ���
			if(distances_cd[i][0]>distances_cd[i][1]) {
				// �����ݵ���ӵ���һ�����ĵ������� 
				k1[a][0] = data[i][0];
				k1[a][1] = data[i][1];
				k1[a][2] = data[i][2];
				a++;// ��������1  
			}
			// �����ǰ���ݵ㵽�ڶ������ĵľ���С�ڻ���ڵ�
			if(distances_cd[i][0]<=distances_cd[i][1]) {
				// �����ݵ���ӵ��ڶ������ĵ�������
				k2[b][0] = data[i][0];
				k2[b][1] = data[i][1];
				k2[b][2] = data[i][2];
				b++;// ��������1  
			}
		}
		// ��ʼ�����յ���ά���飬���ڴ洢�����ķ�������ݵ�
		double k_zhi[][][] = new double[2][a+b][3];
		// ����һ�����������е����ݵ㸴�Ƶ����յ���ά������
		for (int i = 0; i < k1.length ; i++) {
			k_zhi[0][i][0] = k1[i][0];
			k_zhi[0][i][1] = k1[i][1];
			k_zhi[0][i][2] = k1[i][2];
		}
		// ���ڶ������������е����ݵ㸴�Ƶ����յ���ά������
		for (int i = 0; i < k2.length ; i++) {
			k_zhi[1][i][0] = k2[i][0];
			k_zhi[1][i][1] = k2[i][1];
			k_zhi[1][i][2] = k2[i][2];
		}
		// �����յ���ά���鸳ֵ����ĳ�Ա����cluster
		cluster = k_zhi;
		// ���ذ����ķ�������ݵ�����
		return k_zhi; 
	}
	
	/**  
	 * �������ݵ����� `k_zhi` �����µ�����λ�á�  
	 * ���ĳ������û�з��䵽�κ����ݵ㣬���������һ������λ�á�  
	 * @param k_zhi ��ά���飬�洢�����ķ�������ݵ�  
	 * @return �µ�����λ����ɵĶ�ά����  
	 */
	public double[][] find_controid(double k_zhi[][][]) {
		// ����һ���µĶ�ά�������洢�µ�����λ��  
		double new_centroid[][] = new double[k_zhi.length][3]; 
		double sum1 = 0;
		double sum2 = 0;
		double sum3 = 0;
		// ����ÿ������  
		for(int i = 0;i<=k_zhi.length-1;i++) {
			// ��ʼ������������ĺ�Ϊ0 
			sum1 = 0;
			sum2 = 0;
			sum3 = 0;
			// ������ǰ�����µ��������ݵ�  
			for(int j = 0;j<k_zhi[i].length;j++) {
				// �����ݵ�������������ֵ�ֱ��ۼӵ���Ӧ�ĺ���  
				sum1 = sum1+k_zhi[i][j][0];
				sum2 = sum2+k_zhi[i][j][1];
				sum3 = sum3+k_zhi[i][j][2];
			}
			
			// ���ݵ�ǰ���ĵ����ݵ������������µ�����λ��  
			if(i==0) {
				// ����ǵ�һ������ 
				if(a!=0) {
					// �����һ�����������ݵ�
					new_centroid[i][0] = sum1/a;// �����µ�x���� 
					new_centroid[i][1] = sum2/a;// �����µ�y����  
					new_centroid[i][2] = sum3/a;// �����µ�z���� 
				}else if (a==0) {
					// �����һ������û�����ݵ㣬���������һ���µ�����λ��  
					double single_centroid[][] = rn.random_tdenmension(1, 2, 3, 1); 
					new_centroid[i][0] = single_centroid[0][0];// ����������ɵ�x����
					new_centroid[i][1] = single_centroid[0][1];// ����������ɵ�y���� 
					new_centroid[i][2] = single_centroid[0][2];// ����������ɵ�z����
				}
			}
			
			if(i==1) {
				// ����ǵڶ������ģ�ͬ�����  
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
		// �����µ��������� 
		return new_centroid;	
	}
	
	/**  
	 * �����������ݵ㵽���������ĵľ����ƽ���ͣ�SSE����  
	 * @param distances ��ά���飬�洢ÿ�����ݵ㵽�������ĵľ���  
	 * @return SSEֵ�����������ݵ㵽���������ĵľ����ƽ����  
	 */
	//ȡ��SSE��
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
	 * ִ��K-means�����㷨�ĵ������̣�ֱ���㷨������ﵽ������������  
	 *  
	 * @param initial_centroids ��ʼ����λ�õĶ�ά����  
	 * @param data ���ݵ�Ķ�ά����  
	 * @param convergenceThreshold ������ֵ�������ж��㷨�Ƿ�����  
	 * @return ���յ�����λ����ɵĶ�ά����  
	 */
	public double[][] iteration(double initial_centroids[][], double data[][], double convergenceThreshold) {  
	    double current_centroids[][] = initial_centroids;  
	    double previous_SSE = Double.MAX_VALUE; // ��ʼ��һ���ǳ����SSEֵ  
	    double current_SSE;  
	    boolean hasConverged = false;  
	  
	    while (!hasConverged) {  
	        double distances[][] = this.oushi_distance(data, current_centroids);  
	        double k_zhi[][][] = this.point_assign(distances, data);  
	        double new_centroids[][] = this.find_controid(k_zhi);  
	        current_SSE = this.need_iteration(distances);  
	        
	          
	        // ����������������SSE�ı仯С����ֵ������Ϊ�㷨�Ѿ�����  
	        if (Math.abs(previous_SSE - current_SSE) < convergenceThreshold) {  
	            hasConverged = true;  
	        } else {  
	            previous_SSE = current_SSE;  
	            current_centroids = new_centroids; // ��������λ���Խ�����һ�ε��� 
	            
	        }  
	    }
	      
	    // ��ӡ���յ�SSE��ִ��������������  
	    //System.out.println("Final SSE: " + current_SSE);
	    // �����Ҫ���������յ�����λ��  
	     return current_centroids;  
	}
}
	
