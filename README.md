# ProyectoPOO_2022_HMN
 Proyecto del bimestre
Integrantes:
 - [Herrera Lesly](https://github.com/Lesly-liseth)
 - [Motoche Nicole](https://github.com/nicolemotoche29)
 - [Ñaupari Mayra](https://github.com/maypam22)

1. Creacion del form de Administrador, el mismo que consta de tres botones y con su respectiva imagen.
![image](https://user-images.githubusercontent.com/85316345/187300503-09e1e9ee-50b7-49aa-ad50-595e1e78f739.png)
2. Asi queda el form de Administrador.
![image](https://user-images.githubusercontent.com/85316345/187300358-6db6a44a-5cd3-4417-9cfa-34c171ae669d.png)
3. Los botones aparecen automaticamente en la pantalla de Administrador.java, tambien le hacemos la extencion JFrame.
![image](https://user-images.githubusercontent.com/85316345/187300693-21150a84-e9a4-4dad-87ba-2880e9d0989c.png)
4. Nombramos al main con el respectivo titulo.
![image](https://user-images.githubusercontent.com/85316345/187301067-c408def6-529b-4db1-a2bc-95002437f626.png)
5. Se designa las medidas de la pantalla con el titulo con la conexion.
![image](https://user-images.githubusercontent.com/85316345/187301208-ed3bff67-98a7-4eff-9a36-0be900f88c31.png)
6. Damos la funcionalidad a los botones de Bodeguero, Cajero y Usuario
![image](https://user-images.githubusercontent.com/85316345/188523482-34ddc55b-45a1-41db-92aa-7941dd9bc2c6.png)
7. Creacion del form de Bodeguero
8. Designamos los nombres para evitar confusion
![image](https://user-images.githubusercontent.com/85316345/187305612-3ec91f86-98e0-428a-9136-b08b867ff385.png)
9. El form de Bodeguero hecho.
![image](https://user-images.githubusercontent.com/85316345/188523608-875ef1c6-1335-4b57-9169-e425cd07b1a1.png)
10. Las librerias importadas que van a ser usadasen el bodeguero.java
![image](https://user-images.githubusercontent.com/85316345/187305742-d349f67f-bf93-43a2-bc55-cebad6f39160.png)
11. En la clase publica colocamos la extension JFrame
![image](https://user-images.githubusercontent.com/85316345/187305829-07169cee-6e9d-43a2-8358-1fa5e423fe42.png)
12. En la clase publica de bodeguero designamos el titulo y las medidas de la pantalla
![image](https://user-images.githubusercontent.com/85316345/187305920-e4c2b0c2-b607-4e76-bb31-d78c101a8a9c.png)
13. Vamos a dar una funcionalidad a los botones-
![image](https://user-images.githubusercontent.com/85316345/188523686-b29ceee4-64a6-43ea-92eb-c3d1eba70ac0.png)
14. Creamos la clase publica conectar, con el respectivo URL de nuestra base de datos, el username y su password
determinandoun try para mandar un mensaje de "Conexion Exitosa" y un catch de " SQL incorrecto"
![image](https://user-images.githubusercontent.com/85316345/188523814-6abdc100-6334-4c98-a98f-2803fdc25d3f.png)
15. Creamos la clase publica denominada ingresar, con el respectivo string que son nombre, descripcion, precio, cantidad y el stock,
tambien designamos el URL para que se una a nuestra base de datos, de igual manera con un try que contiene los setters
y los mensajes de "Ingreso de Registro Realizado"  y un catch de " SQL incorrecto"
![image](https://user-images.githubusercontent.com/85316345/188523910-d9a1ef16-b91b-4728-ada9-1b4f2f43303e.png)
16. Creamos una clase publica denomida limpiar, donde todoslos apartados van a sereleiminados
![image](https://user-images.githubusercontent.com/85316345/188524243-31887402-7292-4951-b910-c0a4ec648227.png)
17. Creamos una clase publica denomida buscar, donde sera buscada por el id, tambien incluye el URL de la base de datos
con el uso de un try se realizara un statement que incluye al id, donde se utiliza un if -else, donde en el if 
se pondra todos losapartados que se desea buscar como son nombre, descripcion, precio, cantidad, stock. Caso contrario
se hara el uso de else con un mensaje de error que diga "No se encuentra el producto"
![image](https://user-images.githubusercontent.com/85316345/188524256-a23edce7-5b72-4bb0-92f6-91ef83b35ca2.png)
18. Se procedera a cerrar el stm y la conn
![image](https://user-images.githubusercontent.com/85316345/188524494-9da5c7ad-1659-4cec-8a9c-e9524b8be2b2.png)
19. Creamos una clase publica denomida actualizar donde seran afectadaslas partes de id, nombre, descripcion, precio, cantidad, stock
tambien incluye el URL de la base de datos con el uso de un try y con el update se hara a cada uno de los datos, donde lanzara un mensaje que diga
"Registro actualizado" y un catch de " SQL incorrecto"
![image](https://user-images.githubusercontent.com/85316345/188524524-b419cf08-dfbc-4cd4-b1c4-23e9a6197c1d.png)
20. Creamos una clase publica denomida eliminar la cual estara conectada alURL de nuestra base de datos y sera bborrada por medio 
del id, de igual manera se registrara un mensaje "Registro borrado" y un catch de " SQL incorrecto"
![image](https://user-images.githubusercontent.com/85316345/188524687-3dd032ce-76a3-4704-98cf-2dc2b146f4c2.png)
21. Creamos una clase publica denomida ver donde se hara uso de un JTable, en el cual estaenlazada cono todas con la URL de la base de datos.
donde se hara la creacion de una tabla con filas y columnas para que registre cada uno de los datos 
![image](https://user-images.githubusercontent.com/85316345/188524804-cf0d6d5d-54b1-45a5-bdd4-8159d78b4ebc.png)
22. Se procedera a cerrar el stm y la conn y un catch de " SQL incorrecto"
![image](https://user-images.githubusercontent.com/85316345/188524909-2978ebb7-0324-45ef-9b7a-dec7a2975f04.png)
23. Se crea una nueva clase main de bodeguero
![image](https://user-images.githubusercontent.com/85316345/188524959-bdf514d4-814c-4a51-9258-7a0cbaa59255.png)
24. Y un Override parala opcion de ir a login
![image](https://user-images.githubusercontent.com/85316345/188524985-4e054c19-7a0a-4f43-90c6-e84a09487158.png)





