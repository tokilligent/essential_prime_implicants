import java.util.*;
public class project260{
  public static void main(String[]args){
    Method method=new Method(); 
    Scanner sc=new Scanner(System.in);
    
    //Step 1
    
    System.out.println("No of inputs");
    int input_no=sc.nextInt();
    int can_no[]=new int[input_no];
    String []input=new String[input_no];
    
    for(int i=0;i<input.length;i++){
      int a=sc.nextInt();
      can_no[i]=a;
      input[i]=Integer.toBinaryString(a);
      if(input[i].length()<4)
        input[i]=method.extra_bit(input[i]);
    }
    
    //Step 2
    LinkedList initial_g[]=new LinkedList[5];
    LinkedList initial_mem[]=new LinkedList[5];
    for(int i=0;i<initial_g.length;i++){
      initial_g[i]=new LinkedList <String>();
      initial_mem[i]=new LinkedList <String>();
    }
    for(int i=0;i<initial_g.length;i++){
      for(int j=0;j<input.length;j++){
        if(method.one_count(input[j])==i){
          initial_g[i].add(input[j]);
          initial_mem[i].add(Integer.toString((Integer.parseInt(input[j],2))));
        }
      }
    }
    
    //Step 3
    
    LinkedList grp[]=initial_g;
    LinkedList member[]=initial_mem;
    LinkedList <String> ungroup=new LinkedList <String> ();
    LinkedList <String> ungrp_val=new LinkedList<String>();
    for(int l=0;l<5;l++){
      LinkedList arr[]=new LinkedList[5-(l+1)];
      LinkedList team[]=new LinkedList[5-(l+1)];
      for(int i=0;i<arr.length;i++){
        arr[i]=new LinkedList<String>();
        team[i]=new LinkedList<String>(); 
      }
      for(int i=0;i<grp.length-1;i++){
        for(int j=0;j<grp[i].size();j++){
          for(int k=0;k<grp[i+1].size();k++){
            if(method.grp_check((grp[i].get(j)).toString(),(grp[i+1].get(k)).toString())==true){
              arr[i].add(method.new_mem((grp[i].get(j)).toString(),(grp[i+1].get(k)).toString()));
              team[i].add(method.mem_no((member[i].get(j)).toString(),(member[i+1].get(k)).toString()));
            }
          } 
        }
      }

//Process of getting prime implicants
      LinkedList<String> comp_1=new LinkedList<String>();
      LinkedList<String> comp_2=new LinkedList<String>();
      LinkedList<String> comp_1_val=new LinkedList<String>();
      for(int i=0;i<member.length;i++){
        for(int j=0;j<member[i].size();j++){
          comp_1.add((member[i].get(j)).toString());
          comp_1_val.add((grp[i].get(j)).toString());
        }
      }
      for(int i=0;i<team.length;i++){
        for(int j=0;j<team[i].size();j++){
          String a=(team[i].get(j)).toString();
          int c=l+1;
          int idx=0;
          for(int k=0;k<a.length();k++){
            if(a.charAt(k)=='_')
              idx++;
            if(idx==c){
              comp_2.add(a.substring(0,k));
              comp_2.add(a.substring(k+1,a.length()));
              break;
            }
          }
        }
      }
      boolean check[]=new boolean[comp_1.size()];
      for(int i=0;i<comp_1.size();i++){
        for(int j=0;j<comp_2.size();j++){
          if(comp_2.get(j).equals(comp_1.get(i)))
            check[i]=true;
        }
      }
      
      for(int i=0;i<comp_1.size();i++){
        if(check[i]==false){
          ungroup.add((comp_1.get(i)).toString());
          ungrp_val.add((comp_1_val.get(i)).toString());
        }
      }   
      grp=arr;
      member=team;
    }
    //Step 4: Essential Prime Implicants
    
    LinkedHashSet <String> h=new LinkedHashSet <String>();
   
    for(int i=0;i<ungrp_val.size();i++){
      h.add(method.mem_to_ini((ungrp_val.get(i)).toString()));
    }
    String can_elem[]=new String[h.size()];
   
    Iterator<String> H=h.iterator();
   
    int n3=0;
    while(H.hasNext()){
      can_elem[n3++]=H.next();
    }

    // can_no[] contains all the canonical numbers
    // can_elem[] contains all the initial form of prime implicants
    // ungrp_val<> contains all the binary form of prime implicants
    // ungroup<> contains all the group no of prime implicants e.g 1_3
    boolean table[]=new boolean[can_no.length];
    LinkedList <String> epi=new LinkedList <String> ();
    boolean finish_order=false;
    while(finish_order==false){
      int max=0;
      int max_idx=0;
      int ungrp_no=0;
      for(int i=0;i<can_elem.length;i++){
        for(int j=0;j<ungrp_val.size();j++){
          String b=method.mem_to_ini(ungrp_val.get(j));
          String a=can_elem[i];
          if(a.equals(b)==true){
            int arr[]=method.elem_val(ungroup.get(j)); //ex:{0,1,8,9}
            int count=method.cross_check(can_no,arr,table);
            if(count>max){
              max=count;
              max_idx=i;//initial form
              ungrp_no=j;// 1_7_8_9
            }
            break;
          }
        }
      }
      epi.add(can_elem[max_idx]);
      int true_conv[]=method.elem_val(ungroup.get(ungrp_no));
      for(int i=0;i<true_conv.length;i++){
        for(int j=0;j<can_no.length;j++){
          if(true_conv[i]==can_no[j])
            table[j]=true;
        }
      }
      boolean check=false;
      if(method.all_check(table)==true)
        finish_order=true;
    }
    String output="F(a,b,c,d) = ";
    for(int i=0;i<epi.size();i++){
      if(i==epi.size()-1)
        output+=epi.get(i);
      else
        output+=epi.get(i)+" + ";
    }
    System.out.println(output);
  }
}
