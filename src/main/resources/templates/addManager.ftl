<#import "parts/common.ftl" as common>
<#import "parts/registration.ftl" as reg>

<@common.page>
    ${message!}
    <@reg.registration "/createManager" />
</@common.page>