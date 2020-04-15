<#import "parts/common.ftl" as common>
<#import "/spring.ftl" as spring/>

<@common.page>
<div class="flex-container">
  <table class="table cart">
   <tr class="thead-dark ">
        <th scope="col"><@spring.message "productName"/></th>
        <th scope="col"><@spring.message "price"/></th>
        <th scope="col"><@spring.message "quantity"/></th>
        <th scope="col"><@spring.message "totalSum"/></th>
        <th scope="col"><@spring.message "action"/></th>
   </tr>
  </table>
</div>
</@common.page>