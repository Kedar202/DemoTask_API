package com.studentdemo.studentdemo.config;

import java.io.IOException;

class B {
public void print() throws IOException {
        System.out.println("print red");
        }
        }
       public class A extends B {
@Override
public void print() throws Exception {
        System.out.println("print blue");
        }
public static void main (String ...args) {
        try {
        B aObject = new A();
        aObject.print();
        } catch (Exception ex) {
        System.out.println("in catch"); }
        }
        }