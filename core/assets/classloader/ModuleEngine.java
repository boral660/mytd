import java.io.File;

public class ModuleEngine {

  public static void main(String args[]) {
    String modulePath = args[0];
    System.out.print("Module path: ");
    System.out.println(modulePath);
    /**
     * Создаем загрузчик модулей.
     */
    ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());

    /**
     * Получаем список доступных модулей.
     */
    File dir = new File(modulePath);
    String[] modules = dir.list();
    if (modules == null)
    {
        System.out.println("Module path does not denote a folder");
        return;
    }
    /**
     * Загружаем и исполняем каждый модуль.
     */
    for (String module: modules) {
        if (module.endsWith(".class")) {
            try {
                String moduleName = module.split(".class")[0];
                if (moduleName.equals("Module") == false) {
                    System.out.print("Executing loading module: ");
                    System.out.println(moduleName);

                    Class clazz = loader.loadClass(moduleName);
                    Module execute = (Module) clazz.newInstance();

                    execute.load();
                    execute.run();
                    execute.unload();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


  }

}
