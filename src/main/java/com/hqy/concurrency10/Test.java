package com.hqy.concurrency10;

import com.hqy.concurrency10.activeObjectInterface.ActiveObject;

public class Test {

    //main
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakeClientThead(activeObject, "A").start();
        new MakeClientThead(activeObject, "B").start();

        new DisplayClientThread("Chris",activeObject).start();
    }
}
