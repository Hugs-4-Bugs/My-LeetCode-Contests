class Solution {
		public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
			int n = s.length();
			int[] reach = new int[n];
			int p = n;
			int[] f = new int[2];
			for(int i = n-1;i >= 0;i--){
				f[s.charAt(i)-'0']++;
				while(f[0] > k && f[1] > k){
					f[s.charAt(--p)-'0']--;
				}
				reach[i] = p;
			}
			long[] cum = new long[n+1];
			for(int i = 0;i < n;i++){
				cum[i+1] = cum[i] + reach[i];
			}

			long[] ret = new long[queries.length];
			for(int z = 0;z < queries.length;z++) {
				int[] q = queries[z];
				q[1]++;
				int lb = Math.max(q[0], lowerBound(reach, q[1]));
				long sum = (long)q[1] * (q[1] - lb) + cum[lb] - cum[q[0]] - (long)q[1] * (q[1] - 1) / 2 + (long)q[0] * (q[0] - 1) / 2;
				ret[z] = sum;
			}
			return ret;
		}

		public static int lowerBound(int[] a, int v){ return lowerBound(a, 0, a.length, v); }
		public static int lowerBound(int[] a, int l, int r, int v)
		{
			if(l > r || l < 0 || r > a.length)throw new IllegalArgumentException();
			int low = l-1, high = r;
			while(high-low > 1){
				int h = high+low>>>1;
				if(a[h] >= v){
					high = h;
				}else{
					low = h;
				}
			}
			return high;
		}
	}
