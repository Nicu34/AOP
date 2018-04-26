package aspectj;

import java.security.Signature;

public aspect SimpleTracing {
    // Collection of JoinPoint call any method
    // And within SimpleTracingTest
    pointcut tracedCall() : call (* *(..))
            && within(service.EmailService)
            ;

    before() : tracedCall()  {
//        Signature sig = thisJoinPointStaticPart.();
//        String line = ""
//                + thisJoinPointStaticPart.getSourceLocation().getLine();
//
//        String sourceName = thisJoinPointStaticPart.getSourceLocation()
//                .getWithinType().getCanonicalName();

//        System.out.println("Call from " + sourceName + " line " + line + "\n   to "
//                + sig.getDeclaringTypeName() + "." + sig.getName() +"\n");
        System.out.println("hello there");
    }
}