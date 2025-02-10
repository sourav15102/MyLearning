RabbitMQ is a "MQ". It receives messages and delivers messages.

Celery is a Task Queue. It receives tasks with their related data, runs them and delivers the results.

Let's forget Celery for a moment. Let's talk about RabbitMQ. What would we usually do? Our Django/Flask app would send a message to a queue. We will have some workers running which will be waiting for new messages in certain queues. When a new message arrives, it starts working and processes the tasks.

Celery manages this entire process beautifully. We no longer need to learn or worry about the details of AMQP or RabbitMQ. We can use Redis or even a database (MySQL for example) as a message broker. Celery allows us to define "Tasks" with our worker codes. When we need to do something in the background (or even foreground), we can just call this task (for instant execution) or schedule this task for delayed processing. Celery would handle the message passing and running the tasks. It would launch workers which would know how to run your defined tasks and store the results. So you can later query the task result or even task progress when needed.