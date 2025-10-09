# Sistema de Gestión de Productos y Pedidos

# Pre Entrega TechLab Backend

## Descripción

Este proyecto es una aplicación de consola desarrollada en Java que permite realizar operaciones básicas de un CRUD (Crear, Leer, Actualizar y Eliminar) para la gestión de un inventario de productos. El objetivo final es expandir el sistema para incluir la creación y gestión de pedidos.

## Funcionalidades Actuales 💻

- ✅ **Crear producto:** Permite agregar un nuevo producto al inventario (nombre, precio, stock).
- ✅ **Listar productos:** Muestra la lista completa de productos registrados.
- ✅ **Actualizar producto:** Permite modificar los datos de un producto existente buscándolo por su código.
- ✅ **Eliminar producto:** Elimina un producto del inventario a través de su código.
- ✅ **Buscar por nombre:** Filtra y muestra productos cuyo nombre coincida con una búsqueda.
- ✅ **Menú interactivo:** La aplicación se controla a través de un menú de consola simple y claro.

## Estructura del Proyecto (Actual)

El proyecto está organizado en los siguientes paquetes:

- `proyecto/Main.java`: Punto de entrada de la aplicación.
- `proyecto/menu/Menu.java`: Contiene la lógica de la interfaz de usuario en consola.
- `proyecto/model/Productos.java`: Define el modelo para los productos.
- `proyecto/utils/Utilidades.java`: Clase con métodos de apoyo para validaciones y entradas de usuario.

## Cómo Compilar y Ejecutar

1.  **Abre una terminal** y navega hasta el directorio que contiene la carpeta `proyecto`.
2.  **Compila** el proyecto con el siguiente comando:
    ```bash
    javac proyecto/Main.java proyecto/menu/Menu.java proyecto/model/Productos.java proyecto/utils/Utilidades.java
    ```
3.  **Ejecuta** la aplicación con:
    ```bash
    java proyecto.Main
    ```

---

## Tareas Pendientes (To-Do List) 📝

Esta es la lista de tareas necesarias para cumplir con todos los requisitos de la pre-entrega del proyecto.

- ### 1. Refactorización de Paquetes

  - [ ] Reorganizar la estructura de archivos para que coincida con la solicitada:
    - [ ] Mover `Productos.java` a `com.techlab.productos`.
    - [ ] Crear el paquete `com.techlab.pedidos`.
    - [ ] Crear el paquete `com.techlab.excepciones`.
    - [ ] Mover `Main.java` y `Menu.java` a un paquete principal como `com.techlab.main`.

- ### 2. Implementación del Módulo de Pedidos

  - [ ] **Crear el modelo de datos:**
    - [ ] Crear la clase `LineaPedido.java` en `com.techlab.pedidos` para asociar un producto con una cantidad.
    - [ ] Crear la clase `Pedido.java` en `com.techlab.pedidos` para gestionar una lista de `LineaPedido`.
  - [ ] **Integrar en el menú:**
    - [ ] Agregar la opción **"5) Crear un pedido"** al menú principal.
    - [ ] Agregar la opción **"6) Listar pedidos"** al menú principal.
    - [ ] Actualizar la opción de salida al número **"7"**.
  - [ ] **Desarrollar la lógica principal:**
    - [ ] Implementar el método `CrearPedido()` que permita seleccionar productos y cantidades.
    - [ ] **Validar el stock** disponible antes de agregar un producto al pedido.
    - [ ] **Actualizar el stock** de los productos una vez que el pedido se confirma.
    - [ ] Implementar el método `ListarPedidos()` para mostrar los pedidos realizados.

- ### 3. Manejo de Excepciones Avanzado
  - [ ] Crear la excepción personalizada `StockInsuficienteException.java` en el paquete `com.techlab.excepciones`.
  - [ ] Lanzar (`throw`) la excepción `StockInsuficienteException` desde la lógica de creación de pedidos cuando un producto no tenga stock suficiente.
  - [ ] Capturar (`try-catch`) la excepción en la llamada desde el menú para informar al usuario del error de forma controlada.
