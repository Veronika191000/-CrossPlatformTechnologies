/**
 * This class demonstrates first java application
 * @author Abarnikova Veronika <v.abarnikova@student.csn.khai.edu>
 * @version 1.1
 * @since 2020-09-07
 */

public class MainClass {
    public static void main(String[] args) {
        System.out.println("Group: 545a");
        System.out.println("Abarnikova Veronika Oleksiivna");
        System.out.println("Variant: 1");
        String[] symbolsArray =
                {
                        "     **  ***        ***   ********",
                        "    ****  ***      ***   ***    ***",
                        "   **  **  ***    ***    ***    ***",
                        "  ********  ***  ***     ***    ***",
                        " ***    ***  ** **       ***    ***",
                        "***     ****   **         ********",
                };
        for (String str: symbolsArray) {
            System.out.println(str);
        }
    }
}



