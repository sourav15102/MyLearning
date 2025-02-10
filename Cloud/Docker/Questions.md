
Q) if docker compose mentions service name, it calls by service name otherwise by container name?
Q) diff between service adn container
Q why adding ntwork keeeps dns inside?

Please try the following:

1. Go to System Tools => Resource Monitor GUI for windows
2. Check which service is using port **53**,
3. Once you identify it, kill the process as follows: `taskkill /f /pid [PID]`.