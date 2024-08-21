  [[My learnings/Cloud/Docker/Docker|Docker]]

Containerization management platform that is used for deploying, and running application, decoupling the app from infra.

Container:
- bundle of app and dependencies
- use system resources to do run
- aim to remove infra dependency, portable
- runtime instances of docker images

Images:
- blue print of docker container, can be deployed to any docker environment and the containers can be spun up.
- consists of read-only layers each of which represents a Dockerfile instruction. The layers are stacked and each one is a delta of the changes from the previous layer.


Dockerfile
has all commands used to build a docker image, it has a base image, and we can stack our commands on top of it.

Docker compose:
docker-compose is used for creating multiple containers
1. communication between them
2. port opened
3. services, networks, volumes.


Hypervisor:
A hypervisor is a software that managed VMs, and makes virtualization happen
1. **Native Hypervisor:** a Bare-metal Hypervisor and runs directly on the underlying host system which also ensures direct access to the host hardware which is why it does not require base OS.  
2. **Hosted Hypervisor:** This type makes use of the underlying host operating system which has the existing OS installed.

Namespace:
A namespace is basically a Linux feature that ensures mutually exclusive OS resources partition.
In docker, the namespaces ensure that the containers are portable and they don't affect the underlying host
supported ones: PID, Mount, User, Network, IPC.

```
docker image ls
docker ps -a
```

Components:
![[docker_components.png]]

> container must be stopped before removing, paused one cant be removed.

VM vs container:
![[SWTM-2060_Diagram_Containers_VirtualMachines_v03.png]]
Containers:
- lightweight
- uses hosted os
- namespaces
- irtualizes _only_ the application layer,
VM:
- heavyweight
- has own guest os, need booting up
- hypervisors
- virtualizes both the operating system kernel and the application layer.

If you have specific hardware requirements for your project, or you are developing on one hardware platform and need to target another like Windows vs MacOS, you will need to use a virtual machine. Most other 'software only' requirements can be met by using containers.

COPY vs ADD:
They let you copy files from a specific location into a Docker image.  
**COPY** takes in a source and destination. It only lets you copy in a local or directory from your host into the Docker image itself.
```
COPY <src> <dest>
``` 
**ADD**  does that same but in addition, it also supports 2 other sources. 
1. A URL instead of a local file/directory.
2. Extract tar from the source directory into the destination.
```
ADD <src> <dest>
```


Restart container:
Yes, it is possible only while using certain docker-defined policies
1. **Off:** In this, the container won’t be restarted in case it's stopped or it fails.  
2. **On-failure**: Here, the container restarts by itself only when it experiences failures not associated with the user.  
3. **Unless-stopped:** Using this policy, ensures that a container can restart only when the command is executed to stop it by the user.  
4. **Always:** Irrespective of the failure or stopping, the container always gets restarted in this type of policy.

Image vs layer:
**Image:** This is built up from a series of read-only layers of instructions.
**Layer:** Each layer corresponds to an instruction of the image’s Dockerfile.

>  docker info command: The command gets detailed information about Docker installed on the host system, number of containers or images and in what state they are running and hardware specifications like total memory allocated, speed of the processor, kernel version, etc.

Docker compose up, run, start:
up: it starts services, volumes, networks..
run: can run specific services.
start: can start only those services which were created and then stopped.

Daemon vs container level logging:
daemon: give all info during daemon execution
container: can log inside the container.

CMD vs Entrypoint:
Both specify the commands to execute while initializing a container from docker image.
CMD provide default executable, but will be overriden if something else is provided.
```bash
sudo docker run [container_name] [your_name/some_text]
```
But in case of entrypoint, 

![[docker-container-lifecycle.png]]

Jar vs Docker:
Jar:
- used to package java application with its dependencies
- not isolated from infra
- need compatible JVM to run.
- portable as long as compatible JVM is there
- smaller-scale applications
Docker:
- package app and dependencies
- isolated from infra
- microservices architectures, web applications, and complex software ecosystems.