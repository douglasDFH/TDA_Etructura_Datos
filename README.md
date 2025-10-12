# Estructura_dato 📊

**Tipos de Datos Abstractos (TDA) implementados en Java con interfaz gráfica**

## 🎯 Descripción

Este proyecto implementa los principales Tipos de Datos Abstractos (TDA) con una interfaz gráfica intuitiva desarrollada en Java Swing utilizando NetBeans Form Designer.

## 📋 Estructuras de Datos Implementadas

### 🥞 **Pila (Stack)**
- `push()` - Insertar elemento
- `pop()` - Eliminar elemento
- `peek()` - Ver elemento superior
- `isEmpty()` - Verificar si está vacía
- `size()` - Obtener tamaño

### 🔗 **Lista Simple**
- `insertar()` - Insertar al inicio
- `insertarOrdenado()` - Insertar manteniendo orden ascendente
- `insertarDerecha()` - Insertar al final
- `eliminar()` - Eliminar del inicio
- `eliminarDerecha()` - Eliminar del final
- `buscar()` - Buscar elemento
- `ordenarAscendente()` - Ordenar lista
- `ordenarPorReferencia()` - Ordenar usando referencias
- `sumarElementos()` - Sumar todos los valores
- `vaciar()` - Limpiar la lista

### 🔄 **Lista Doble**
- `insertar()` - Insertar al inicio
- `eliminar()` - Eliminar del inicio
- `buscar()` - Buscar elemento
- `vaciar()` - Limpiar la lista

## 🖥️ Interfaz Gráfica

- **Sistema de pestañas** para alternar entre diferentes estructuras
- **Visualización gráfica** en tiempo real de las estructuras
- **Controles adaptativos** según la estructura seleccionada
- **Interfaz intuitiva** con botones organizados en la parte inferior

## 🚀 Ejecución

### Prerrequisitos
- Java 21 o superior
- NetBeans IDE (recomendado para editar formularios)

### Compilación y Ejecución

```bash
# Compilar
javac -cp src\main\java -d target\classes src\main\java\negocio\*.java src\main\java\presentacion\*.java

# Ejecutar
java -cp target\classes presentacion.frmprincipal
```

### Con Maven
```bash
mvn compile exec:java
```

## 📁 Estructura del Proyecto

```
TDAPila/
├── src/main/java/
│   ├── negocio/           # Lógica de TDAs
│   │   ├── clsPila.java
│   │   ├── clsListaSimple.java
│   │   ├── clsListaDoble.java
│   │   ├── clsNodo.java
│   │   └── clsNodoDoble.java
│   └── presentacion/      # Interfaz gráfica
│       ├── frmprincipal.java
│       └── frmprincipal.form
├── pom.xml               # Configuración Maven
└── README.md
```

## 🔧 Tecnologías Utilizadas

- **Java 21** - Lenguaje de programación
- **Swing** - Framework para interfaz gráfica
- **NetBeans Form Designer** - Diseño visual de formularios
- **Maven** - Gestión de dependencias y construcción

## 📸 Características

- ✅ Implementación completa de TDAs fundamentales
- ✅ Interfaz gráfica con sistema de pestañas
- ✅ Visualización en tiempo real de las estructuras
- ✅ Controles adaptativos por tipo de estructura
- ✅ Operaciones avanzadas (ordenamiento, suma, etc.)
- ✅ Código bien documentado y estructurado

## 📄 Licencia

Este proyecto fue desarrollado con fines educativos para el estudio de Estructuras de Datos y Algoritmos.

## 👨‍💻 Autor

Proyecto desarrollado como parte del estudio de Tipos de Datos Abstractos y programación orientada a objetos en Java.