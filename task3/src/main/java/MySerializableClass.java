import java.io.*;
import java.util.Objects;

public class MySerializableClass implements Serializable {
    private simpleThing simpleField;

    public MySerializableClass(simpleThing simpleField) {
        this.simpleField = simpleField;
    }

    public MySerializableClass deepClone() {
        try {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serialize.txt"));
            oos.writeObject(this);
            oos.close();

//            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serialize.txt"));
            MySerializableClass that = (MySerializableClass) ois.readObject();
            ois.close();
            return that;

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "MySerializableClass{" +
                "simpleField=" + simpleField +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySerializableClass that = (MySerializableClass) o;
        return Objects.equals(simpleField, that.simpleField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simpleField);
    }

    public simpleThing getSimpleField() {
        return simpleField;
    }

    public void setSimpleField(simpleThing simpleField) {
        this.simpleField = simpleField;
    }
}
