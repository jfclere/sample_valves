sample valves
=============

example of valves

to install: (in AS7 modules).
```bash
mkdir -p modules/samplevalves/main
cp module.xml modules/samplevalves/main
cp target/samplevalves-1.0-SNAPSHOT.jar modules/samplevalves/main
```

in standalone.xml add the valve:
```xml
<valve name="myvalve" class-name="org.jboss.samplevalves.restrictedUserAgentsValve" module="samplevalves">
  <param param-name="restricteduseragents" param-value="^.*MS Web Services Client Protocol.*$"/>
</valve>
```

to test:
```
telnet localhost 8080
GET / HTTP/1.1
Host: localhost
User-Agent: MS Web Services Client Protocol.1.0
```

the response should contain:

