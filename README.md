# PL2

## Asignatura: Estructuras de Datos

## Grupo: H12GEXTRA

## Integrantes
- Marcos Castro Rubio - [Marcoscastro9]
- Ventura Pacheco Pastilla - [Ventura1107]
- Marino Rodríguez Moreno - [Marinakis14]

## Lenguaje: java

## Entorno: IntelliJ

## Diagrama UML simplificado
Dentro de las carpetas correspondientes del arbol binario de búsqueda y del grafo, se encuentran diagramas UML mas 
elaborados junto con las clases main y las clases de prueba que no aparecen en este diagrama.

```mermaid
%%{init: {'theme': 'neutral'}}%%
classDiagram
    direction TB

    %% 1. DEFINICIÓN MANUAL DEL ESTILO (Fondo blanco, borde negro)
    classDef cleanNode fill:#ffffff,stroke:#333333,stroke-width:2px,color:#000000;

    %% ARBOL

    %% Jerarquía de Clases
    InterfazArbol <|.. ArbolBinarioDeBusqueda
    ArbolBinarioDeBusqueda <|-- ArbolBinarioDeBusquedaEquilibrado

    namespace Hoja2a.arbolesbinarios {
        class InterfazArbol {
            <<interface>>
        }
    
        class ArbolBinarioDeBusqueda {
            -Nodo~T~ raiz
        }
    
        class ArbolBinarioDeBusquedaEquilibrado {
            %% Clase especializada en balanceo (AVL/Red-Black)
        }
    
        class Nodo {
            -Nodo~T~ izquierda
            -Nodo~T~ derecha
            -ListaSimplementeEnlazada~T~ datos
        }
    }

    namespace MisEstructurasDeDatos {
        class ListaSimplementeEnlazada {
            -Elemento~T~ primero
            -int size
        }
    
        class Elemento {
            -T dato
            -Elemento~T~ siguiente
        }
    }

    %% Relaciones Estructurales (Árbol)
    ArbolBinarioDeBusqueda "1" o-- "0..1" Nodo
    Nodo "1" o-- "0..1" Nodo
    Nodo "1" o-- "0..1" Nodo
    Nodo "1" *-- "1" ListaSimplementeEnlazada
    
    ArbolBinarioDeBusqueda ..> ListaSimplementeEnlazada : genera resultados
    ListaSimplementeEnlazada "1" *-- "n" Elemento : contiene

    %% GRAFO

    %% Relaciones de Herencia e Interfaz
    InterfazGrafo <|.. Grafo
    InterfazDatosNodo <|.. DatoNodo
    InterfazDatosArista <|.. DatoArista
    InterfazDatosArista <|.. DatoAristaConPeso
    InterfazAristasConPeso <|.. DatoAristaConPeso

    namespace Hoja2b.Grafos {
        class InterfazGrafo {
            <<interface>>
        }
        class InterfazDatosNodo {
            <<interface>>
        }
        class InterfazDatosArista {
            <<interface>>
        }
        class InterfazAristasConPeso {
            <<interface>>
        }
    
        class Grafo {
            -Lista~NodoGrafo~ nodos
            -Lista~Arista~ aristas
        }
    
        class NodoGrafo {
            -long id
            -DN datos
        }
    
        class Arista {
            -long id
            -NodoGrafo origen
            -DA datos
            -NodoGrafo destino
        }
    
        class DatoNodo {
            -String nombre
            -String tipo
        }
    
        class DatoArista {
            -String dato
        }
    
        class DatoAristaConPeso {
            -int peso
            -String dato
        }
    
        class DatoDijkstra {
            +NodoGrafo nodo
            +int distancia
        }
    
        class DatosGrafoJson {
            -String[] tipos
            -TripletaJson[] tripletas
        }
    
        class TripletaJson {
            -String s
            -String p
            -String o
        }
    }

    %% Relaciones Estructurales (Grafo)
    Grafo "1" *-- "n" NodoGrafo
    Grafo "1" *-- "n" Arista
    Arista "n" o-- "1" NodoGrafo : origen
    Arista "n" o-- "1" NodoGrafo : destino
    Grafo ..> DatoDijkstra : utiliza
    DatosGrafoJson "1" *-- "n" TripletaJson
    LectorGrafoJson ..> Grafo : crea
    LectorGrafoJson ..> DatosGrafoJson : lee

    %% 2. APLICACIÓN MANUAL DEL ESTILO A CADA CLASE
    class InterfazArbol,ArbolBinarioDeBusqueda,ArbolBinarioDeBusquedaEquilibrado,Nodo,ListaSimplementeEnlazada,Elemento cleanNode;
    class InterfazGrafo,InterfazDatosNodo,InterfazDatosArista,InterfazAristasConPeso,Grafo,NodoGrafo,Arista,DatoNodo,DatoArista,DatoAristaConPeso,DatoDijkstra,DatosGrafoJson,TripletaJson cleanNode;
```