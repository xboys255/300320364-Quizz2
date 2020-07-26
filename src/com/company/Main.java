package com.company;

import java.util.*;

import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
import java.nio.BufferUnderflowException;
import java.nio.file.*;
import java.util.*;

import java.io.*;
import java.nio.file.Path;

import static java.util.stream.Collectors.toMap;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        Path file = Paths.get("employee.txt");

        if (!Files.exists(file)) {
            OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.CREATE));
            outputStream.flush();
            outputStream.close();
        }
        HashMap<String, List<String>> employee = new HashMap<String, List<String>>();
        HashMap<String, List<String>> employee1 = new HashMap<String, List<String>>();
//        HashMap<String, String[]> employee = new HashMap<String, String[]>();

        Scanner input = new Scanner(System.in);

        String name, s = "";
        String empType, choiceType;
        String delimiter = "-";
        String array[] = new String[10];


        double sale = 0;
        InputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new BufferedInputStream(Files.newInputStream(file));
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            s = bufferedReader.readLine();
            while (s != null) {
                array = s.split(delimiter);
                List<String> data = Arrays.asList(array[1], array[2], array[3]);
                employee.put(array[0], data);
                employee1.put(array[0], data);
//                System.out.println(s);

                s = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("employee.txt", true));


        while (true) {
            try {

                System.out.println("(A)dd Employee, (R)emove Employee, (M)odify Employee, (C)ompute or (Q)uit");
                String choice = input.nextLine();


                if (choice.equalsIgnoreCase("a")) {
                    System.out.println("Enter Employee name");
                    name = input.nextLine();

                    if (employee.containsKey(name)) {

                        System.out.println("User Already existed");

                    } else {
                        System.out.println("Enter the Employee type (F for Full-time and P for Part-time) ");
                        empType = input.nextLine();
                        if (empType.equalsIgnoreCase("f")) {
                            System.out.println("Enter the employee sales");
                            sale = input.nextDouble();
                            input.nextLine();
                            FullTime f = new FullTime(name, sale, empType.toUpperCase());

                            String[] data = new String[]{f.getEmpType(), f.getSale(), f.getCommision()};
                            List<String> datalist = Arrays.asList(data);
                            employee.put(name, datalist);

                        } else if (empType.equalsIgnoreCase("p")) {
                            System.out.println("Enter the employee sales");
                            sale = input.nextDouble();
                            input.nextLine();

                            FullTime p = new FullTime(name, sale, empType.toUpperCase());

                            String[] data = new String[]{p.getEmpType(), p.getSale(), p.getCommision()};
                            List<String> datalist = Arrays.asList(data);
                            employee.put(name, datalist);

                        }
                        System.out.println("Record Updated");


                        String data = "", data1 = "", data2 = "", data3 = "";
                        for (Map.Entry<String, List<String>> entry : employee.entrySet()) {
                            data = entry.getKey();

                            if (employee1.containsKey(data)) {
                                continue;
                            } else {
                                data = entry.getKey() + "-";
                                data1 = entry.getValue().get(0).toString() + "-";
                                data2 = entry.getValue().get(1).toString() + "-";
                                data3 = entry.getValue().get(2).toString();
                                try {

                                    bufferedWriter.write(data);
                                    bufferedWriter.write(data1);
                                    bufferedWriter.write(data2);
                                    bufferedWriter.write(data3);
                                    bufferedWriter.newLine();
                                } catch (Exception e) {
                                    System.out.println(e.getStackTrace());
                                } finally {

                                    bufferedWriter.close();
                                }
                            }


                        }


                    }
                    continue;
                }
                if (choice.equalsIgnoreCase("r")) {
                    System.out.println("Enter employee name to delete");
                    name = input.nextLine();
                    if (!employee.containsKey(name)) {

                        System.out.println("Employee doesn't existed");
                        continue;

                    } else {
                        System.out.println("Do you really want to delete this record? Y/N ");
                        choiceType = input.nextLine();

                        if (choiceType.equalsIgnoreCase("y")) {
                            employee.remove(name);
                        } else {
                            continue;
                        }
                    }


                }
                if (choice.equalsIgnoreCase("m")) {
                    System.out.println("Enter employee name ");
                    name = input.nextLine();


                    if (!employee.containsKey(name) && !employee1.containsKey(name)) {

                        System.out.println("User doesn't existed");
                        continue;
                    } else {
                        System.out.println("Do you want to edit the sale amount or the type? Enter T for type and S for sale amount");
                        choiceType = input.nextLine();

                        if (choiceType.equalsIgnoreCase("t")) {
                            for (Map.Entry<String, List<String>> entry : employee.entrySet()) {

                                String[] data2 = new String[entry.getValue().size()];
                                data2 = entry.getValue().toArray(data2);
                                System.out.println("Enter the new type (F for full-time and P for part-time) :");
                                String userchoice = input.nextLine();
//                                    employee.remove(name);

                                if (userchoice.equalsIgnoreCase("f")) {

                                    List<String> newList = employee.get(name);
                                    newList.set(0, "F");

                                    double newCommission = Double.parseDouble(data2[1]) * 0.15;
                                    data2[2] = Double.toString(newCommission);
                                    newList.set(2, data2[2]);
                                    employee.replace(name, newList);

                                    continue;
                                } else if (userchoice.equalsIgnoreCase("p")) {

                                    List<String> newList = employee.get(name);
                                    newList.set(0, "P");

                                    double newCommission = Double.parseDouble(data2[1]) * 0.1;
                                    data2[2] = Double.toString(newCommission);
                                    newList.set(2, data2[2]);
                                    employee.replace(name, newList);

                                }


                            }
                        }
                        if (choiceType.equalsIgnoreCase("s")) {

                            for (Map.Entry<String, List<String>> entry : employee.entrySet()) {
                                if (entry.getKey().equalsIgnoreCase(name)) {

                                    String[] data2 = new String[entry.getValue().size()];
                                    data2 = entry.getValue().toArray(data2);
                                    System.out.println("Enter the new sale amount:");
                                    sale = input.nextDouble();
                                    input.nextLine();
                                    data2[1] = Double.toString(sale);
                                    List<String> newList = employee.get(name);
                                    newList.set(1, data2[1]);

                                    if (data2[0].equalsIgnoreCase("f")) {


                                        String newCommission = String.format("%.2f", Double.parseDouble(data2[1]) * 0.15);
                                        data2[2] = newCommission;
                                        newList.set(2, data2[2]);
                                        employee.replace(name, newList);

//
                                    } else if (data2[0].equalsIgnoreCase("p")) {


                                        String newCommission = String.format("%.2f", Double.parseDouble(data2[1]) * 0.1);
                                        data2[2] = newCommission;

                                        newList.set(2, data2[2]);
                                        employee.replace(name, newList);

//
                                    }
                                }
                            }

                        }
                        continue;

                    }

                }
                if (choice.equalsIgnoreCase("c")) {


//                    for (String name1 : employee.keySet()){
//                        System.out.println("Name" + name1 +"Employee " + (String)employee.get(name1).get(0)[0]);
//                        String[] currentEmployee = (String[]) employee.get(name1).get(0);
//                        String empTypee = (String) currentEmployee[0];
//                    }
                    for (Map.Entry<String, List<String>> entry : employee.entrySet()) {
                        System.out.println("Employee Name:  " + entry.getKey());
//                        System.out.println(employee.get(entry).get(0));
                        for (int i = 0; i < employee.get(entry.getKey()).size(); ++i) {

                            System.out.println("Employee Type: " + employee.get(entry.getKey()).get(i));
                            ++i;
                            System.out.println("Employee Sale: " + employee.get(entry.getKey()).get(i));
                            ++i;
                            System.out.println("Employee Commission: " + employee.get(entry.getKey()).get(i));
                            ++i;

                        }

                    }
                    continue;


                }

                if (choice.equalsIgnoreCase("q")) {
                    System.out.println("Thank you");
                    break;
                } else {
                    System.out.println("Invalid entry");
                    continue;
                }


            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                e.getStackTrace();
            }

        }
    }


}
