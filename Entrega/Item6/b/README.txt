<!-- A�ADIR ESTE CONECTOR AL SERVER.XML-->

<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
clientAuth="false" sslProtocol="TLS"
keystoreFile="C:\key\keys" keystorePass="acme1234" />