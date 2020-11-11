import java.util.Scanner;
import java.util.Stack;

/*
* I  Using a Stack to Evaluate an Expression.I use two stack ;one for operators and one for operands .
* */
public class Cal {
static double resultInMemory;//M for string a result like a calculator.
    static double resultInMemoryTemp;

    public  static  void main(String args[])
    {
        Boolean exitFlag=false;//Flag for exit app.
        welcomeAndInfo();
        do {

            resultInMemory =simpleCal();
            resultInMemoryManager();
            System.out.println("Do you want to calucate advance Sin,Cos;tag?Y/N");
            Scanner an=new Scanner(System.in);
            String yes= an.next();
            if(yes.charAt(0)=='y')
            calAdvanced();
            exitFlag=exit();
        }while (!exitFlag);//loop is keeping on until exitflag be true.
    }//End of main

    public static boolean exit()//Method for exit from app.
    {
        boolean answeFlag=false;
System.out.println("Do you want to continue calculating? Y/N");
        Scanner sc=new Scanner(System.in);
        boolean flagNoInputError=false;
        while(!flagNoInputError) {
            String answer = sc.next();
            answer = answer.toLowerCase();
            if (answer.charAt(0) == 'n') {
                answeFlag = true;
                System.out.println("Thank you for using calculator, Have good day! ");
                flagNoInputError=true;
            }
            else if (answer.charAt(0) == 'y')
            {
                answeFlag = false;
                flagNoInputError=true;
            }

                else
            System.out.println("Please enter Y for Yes or N for No");//Error handling for input.
        }//End of while
        return answeFlag;
    }

    public static double simpleCal()
    {double result = 0;

        Scanner scan = new Scanner(System.in);
        /* Create stacks for operators and operands */
        Stack<Integer> operatorStack  = new Stack<Integer>();
        Stack<Double> operandStack = new Stack<Double>();
        //Create temp stacks for operators and operands.
        Stack<Integer> operatorStackTemp  = new Stack<Integer>();
        Stack<Double> operandsStackTemp = new Stack<Double>();

        System.out.println("Enter expression: \n");
        String inputExpressionString = scan.next();// An Expression as string.
        inputExpressionString = "0" + inputExpressionString;
        inputExpressionString = inputExpressionString.replaceAll("-","+-");
        // Store operands and operators in respective stacks.
        String temp = "";
        for (int i = 0;i < inputExpressionString.length();i++)
        {
            char ch = inputExpressionString.charAt(i);
            if (ch == '-')
                temp = "-" + temp;
            else if (ch != '+' &&  ch != '*' && ch != '/')
                temp = temp + ch;
            else
            {
                operandStack.push(Double.parseDouble(temp));
                operatorStack.push((int)ch);
                temp = "";
            }
        }
        operandStack.push(Double.parseDouble(temp));
        //* Create char array of operators as per precedence . -ve sign is already taken care of while storing.
        char operators[] = {'/','*','+'};// Evaluation of expression.

        for (int i = 0; i < 3; i++)
        {
            boolean it = false;
            while (!operatorStack.isEmpty())
            {
                int optr = operatorStack.pop();
                double v1 = operandStack.pop();
                double v2 = operandStack.pop();
                if (optr == operators[i])    // if operator matches evaluate and store in temporary stack.

                {
                    if (i == 0)
                    {
                        operandsStackTemp.push(v2 / v1);
                        it = true;
                        break;
                    }
                    else if (i == 1)
                    {
                        operandsStackTemp.push(v2 * v1);
                        it = true;
                        break;
                    }
                    else if (i == 2)
                    {
                        operandsStackTemp.push(v2 + v1);
                        it = true;
                        break;
                    }
                }
                else
                {
                    operandsStackTemp.push(v1);
                    operandStack.push(v2);
                    operatorStackTemp.push(optr);
                }
            }
            // Push back all elements from temporary stacks to main stacks.
            while (!operandsStackTemp.isEmpty())
                operandStack.push(operandsStackTemp.pop());
            while (!operatorStackTemp.isEmpty())
                operatorStack.push(operatorStackTemp.pop());
            if (it)
                i--;
        }
      result=operandStack.pop();
        System.out.println("\nResult = "+result);//print Result
        return result;//return Result
    }

    public static void welcomeAndInfo()//the method for first time using app and information about how to use it.
    {
        System.out.println("Hi and welcome to the Calculator App!\nYou can enter as many numbers you wish.You cant use bracket ().A example of input can be like 5+6*6.5+9-8/7.5\nYou need to use . for decimal numbers for example 4.5 .\n use of the four mathematical operands (+, -, *, /) ");
        System.out.println("Please enter your first expression");
    }

    public static void resultInMemoryManager()//the method for storing and clearing a AC .
    {
System.out.println(" The result had been stored . Do you want to keep the result for next time calculation  ? Y/N");
Scanner sc=new Scanner(System.in);
boolean flagNoInputError=false;
while(!flagNoInputError) {
    String answer = sc.next();
    answer = answer.toLowerCase();
    if (answer.charAt(0) == 'y')
    {
        resultInMemoryTemp = resultInMemory;
        flagNoInputError=true;
    }
    else if (answer.charAt(0) == 'n') {
        System.out.println("The result was : " + resultInMemory + " and it had been cleared now.");
        resultInMemory = 0;
        flagNoInputError=true;


    } else
        System.out.println("Please enter Y for Yes or N for No");//Error handling for input.
}//End of while
    }

    public static void calAdvanced()
    {
     double a=Math.toRadians(resultInMemory);
     double resultAdvance;
     boolean flagNoinputErroe=false;
        System.out.println("PLease enter S for Sinos , C for cosinus , L for tangae ");

        Scanner sc=new Scanner(System.in);
     while(!flagNoinputErroe)
     {
         String answer = sc.next();
         answer = answer.toLowerCase();
         switch (answer.charAt(0)) {
             case 's':
               resultAdvance=  Math.sin(a);
               flagNoinputErroe=true;
                 System.out.println("Sinus is : "+resultAdvance);

                 break;
             case 'c':
                resultAdvance= Math.cos(a);
                 flagNoinputErroe=true;
                 System.out.println("Cosinos is : "+resultAdvance);

                 break;

             case 'l':
                resultAdvance= Math.tan(a);
                 flagNoinputErroe=true;
                 System.out.println("Tangae is : "+resultAdvance);

                 break;



             default:
                 System.out.println("PLease enter valid input");
         }//End of switch
     }//End of while

    }
}//End of Class
