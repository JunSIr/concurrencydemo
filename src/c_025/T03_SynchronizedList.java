package c_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* Colloections  -> 集合工具类
* .synchronizedXXX
* */

public class T03_SynchronizedList {
    List<String> srts=new ArrayList<>();
    List<String> strsSync= Collections.synchronizedList(srts);
}
