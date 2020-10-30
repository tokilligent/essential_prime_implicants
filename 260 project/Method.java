public class Method{
  public String extra_bit(String a){
    while(a.length()<4){
      a="0"+a;
    }
    return a;
  }
  
  public int one_count(String a){
    int count=0;
    for(int i=0;i<a.length();i++){
      if(a.charAt(i)=='1')
        count++;
    }
    return count;
  }
  public boolean grp_check(String a, String b){
    int c=0;
    for(int i=0;i<a.length();i++){
      if(a.charAt(i)==b.charAt(i))
        c++;
    }
    if(c==3)
      return true;
    else
      return false;
  }
  public String new_mem(String a,String b){
    String z="";
    for(int i=0;i<a.length();i++){
      if(a.charAt(i)==b.charAt(i))
        z=z+a.charAt(i);
      else
        z=z+"X";
    }
    return z;
  }
  public String mem_no(String a,String b){
    String z=a+"_"+b;
    return z;
  }
  public String mem_to_ini(String a){
    String b="";
    for(int i=0;i<a.length();i++){
      if(a.charAt(i)=='1')
        b=b+Character.toString((char)(65+i));
      else if(a.charAt(i)=='0')
        b=b+Character.toString((char)(65+i))+"'";
    }
    return b;
  }
  public int[] elem_val(String a){
    int arr_size=0;
    for(int i=0;i<a.length();i++){
      if(a.charAt(i)=='_')
        arr_size++;
    }
    int arr[]=new int[arr_size+1];
    int n=0;int st_idx=0;
    for(int i=0;i<a.length();i++){
      if(a.charAt(i)=='_'){
        String b=a.substring(st_idx,i);
        st_idx=i+1;
        int num=Integer.parseInt(b);
        arr[n++]=num;
      }
      if(i==a.length()-1){
        String b=a.substring(st_idx,i+1);
        int num=Integer.parseInt(b);
        arr[n++]=num;
      }
    }
    return arr;
  }
  public int cross_check(int[] a,int[] b,boolean[] c){
    int count=0;
    for(int i=0;i<b.length;i++){
      for(int j=0;j<a.length;j++){
        if(b[i]==a[j]){
          if(c[j]==false){
            count++;
          }
        }
      }
    }
    return count;
  }
  public boolean all_check(boolean[] a){
    boolean c=true;
    for(int i=0;i<a.length;i++){
      if(a[i]==false){
        c=false;
        break;
      }
    }
    return c;
  }
}
