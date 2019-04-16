cd ..
mvn eclipse:clean eclipse:eclipse 
mvn clean install -U -Dmaven.test.skip=true
rm -f webapp/WEB-INF/lib/*
mvn dependency:copy-dependencies -DoutputDirectory=webapp/WEB-INF/lib -DincludeScope=runtime -Dsilent=true -DexcludeGroupIds=jspapi,javax.servlet