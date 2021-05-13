import java.util.*;

class basics {

    static List<String> justifyPrint(List<String> listWord, int width)
    {
        List<String> justifiedOutput = new ArrayList<>();
        int count=0,last=0, i;
        for(i=0;i<listWord.size();i++)
        {
            int listWordLength=listWord.get(i).length();
            count+=listWordLength;

            if(count+i-last>width)
            {
                int wordLength = count-listWordLength;
                int spaceLength = width-wordLength;
                int eachLength =1, extraLength=0 ;

                if(i-last-1>0)
                {
                    eachLength = spaceLength/(i-last-1);
                    extraLength = spaceLength%(i-last-1);
                }
                StringBuilder s = new StringBuilder();

                for(int k=last; k<i-1; k++){
                    s.append(listWord.get(k));

                    int addspace = 0;
                    while(addspace<eachLength){
                        s.append(" ");
                        addspace++;
                    }

                    if(extraLength>0){
                        s.append(" ");
                        extraLength--;
                    }
                }

                s.append(listWord.get(i-1));
                while(s.length()<width)
                {
                    s.append(" ");
                }
                justifiedOutput.add(s.toString());
                last=i;
                count=listWordLength;
            }
        }

        StringBuilder sb = new StringBuilder();

        for(i=last; i<listWord.size()-1; i++){
            count = count+listWord.get(i).length();
            sb.append(listWord.get(i)).append(" ");
        }

        sb.append(listWord.get(listWord.size()-1));
        while(sb.length()<width){
            sb.append(" ");
        }
        justifiedOutput.add(sb.toString());
        return justifiedOutput;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> listWords = Arrays.asList(sc.nextLine().split(" "));
        int l = sc.nextInt();
        List<String> result=(justifyPrint(listWords, l));
        for(String i: result)
        {
            System.out.println(i);
        }
    }
}
