import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;

public class mainClass {

    private ArrayList<Integer> intList = new ArrayList<Integer>();
    private HashSet<String> stringSet = new HashSet<String>();
// сериализация через файл -- добавить
    private static void subTask1() {
        MySerializableClass a = new MySerializableClass(new simpleThing("Hello, World!"));
        MySerializableClass b = a.deepClone();
        System.out.println("a == b? " + (a == b));
        System.out.println("a equals b? " + (a.equals(b)));
    }

    private static void subTask2() {
        try {
            Point p1 = new Point(4, 11);
        } catch (MyCheckedException e) {
            e.printStackTrace();
        }
    }

    private static void subTask3() throws NoSuchFieldException {
// jackson -- для json
        Field integerListField = mainClass.class.getDeclaredField("intList");
        ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
        Class<?> integerListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
        System.out.println(integerListClass);

        Field stringHashSetField = mainClass.class.getDeclaredField("stringSet");
        ParameterizedType stringHashSetType = (ParameterizedType) stringHashSetField.getGenericType();
        Class<?> stringHashSetClass = (Class<?>) stringHashSetType.getActualTypeArguments()[0];
        System.out.println(stringHashSetClass);
    }


    public static void main(String Args[]) throws NoSuchFieldException {
        subTask1();
        subTask2();
        subTask3();
    }
}
