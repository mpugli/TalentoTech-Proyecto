# Sistema de Gestión de Productos y Pedidos

## Pre Entrega TechLab Backend

### Descripción

Este proyecto es una aplicación de consola desarrollada en Java que permite realizar operaciones de un CRUD (Crear, Leer, Actualizar y Eliminar) para la gestión de un inventario de productos. El sistema también soporta la creación y visualización de pedidos, validando el stock de productos en tiempo real.

---

### Funcionalidades Implementadas 💻

-   ✅ **Gestión de Productos (CRUD):**
    -   **Crear producto:** Permite agregar un nuevo producto al inventario.
    -   **Listar productos:** Muestra la lista completa de productos registrados.
    -   **Eliminar producto:** Elimina un producto del inventario a través de su código.

-   ✅ **Búsqueda y Actualización Unificada:**
    -   Busca productos por coincidencias en el **nombre**.
    -   Si se encuentran múltiples resultados, permite al usuario **seleccionar por ID** para desambiguar.
    -   Una vez seleccionado un producto, ofrece la opción de **modificar** sus datos.

-   ✅ **Módulo de Pedidos:**
    -   **Crear Pedido:** Un flujo interactivo que permite al usuario añadir múltiples productos a un "carrito". El sistema **valida el stock** disponible para cada artículo (considerando lo que ya está en el carrito) antes de agregarlo.
    -   **Confirmación y Descuento de Stock:** Una vez armado el pedido, se muestra un resumen y se solicita una confirmación final. Si el usuario acepta, el **stock de los productos se actualiza** correctamente.
    -   **Listar Pedidos:** Muestra un historial de todos los pedidos que han sido confirmados.

-   ✅ **Menú Interactivo:** La aplicación se controla a través de un menú de consola simple y claro.

---

### Estructura del Proyecto (Actual)

El proyecto está organizado en los siguientes paquetes:

-   `proyecto/menu/Menu.java`: Contiene la lógica de la interfaz de usuario.
-   `proyecto/model/Producto.java`: Define el modelo para los productos.
-   `proyecto/model/Pedido.java`: Modela un pedido completo.
-   `proyecto/model/LineaPedido.java`: Modela una línea específica dentro de un pedido.
-   `proyecto/utils/Utilidades.java`: Métodos de apoyo para validaciones y entradas de usuario.
-   `proyecto/Main.java`: Punto de entrada de la aplicación.

---

### Cómo Compilar y Ejecutar

1.  **Abre una terminal** y navega hasta el directorio raíz del proyecto.
2.  **Compila** todo el proyecto con el siguiente comando:
    ```bash
    javac proyecto/Main.java proyecto/menu/Menu.java proyecto/model/*.java proyecto/utils/Utilidades.java
    ```
3.  **Ejecuta** la aplicación con el nombre completo de la clase principal:
    ```bash
    java proyecto.Main
    ```

---

### Tareas Pendientes (To-Do List) 📝

Estos son los últimos requerimientos técnicos para completar la pre-entrega.

-   #### 1. Refactorización de Paquetes
    -   [ ] Reorganizar la estructura de archivos para que coincida con la convención estándar `com.techlab.*`.

-   #### 2. Manejo de Excepciones Avanzado
    -   [ ] Crear la excepción personalizada `StockInsuficienteException.java` en un nuevo paquete `com.techlab.excepciones`.
    -   [ ] Lanzar (`throw`) esta excepción desde la lógica de creación de pedidos cuando un producto no tenga stock suficiente.
    -   [ ] Capturar (`try-catch`) la excepción en el menú para informar al usuario del error de una forma más robusta y controlada.