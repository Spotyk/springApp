<#import "parts/common.ftl" as common>
<@common.page>

<div class="flex-container">
  <table class="table">
   <tr class="thead-dark ">
    <th scope="col">Username</th>
    <th scope="col">Email</th>
    <th scope="col">Country</th>
    <th scope="col">State</th>
    <th scope="col">Update</th>
   </tr>
   <#list users as user>
       <tr>
           <form class="form" method="POST" action="/changeUserInfo">
               <input class="form-control id" type="hidden" name="id" value="${user.id}">
               <td>
                   <input type="text" class="form-control username" name="username" value="${user.username}" disabled>
               </td>
               <td>
                    <input type="text" class="form-control email" name="email" value="${user.email}" disabled>
               </td>
               <td>
                   <input type="text" class="form-control country" name="country" value="${user.country}" disabled>
               </td>
               <td>
                   <input type="text" class="form-control state" name="state" value="${user.state}" disabled>
               </td>
               <td>
                   <button class="btn btn-outline-secondary changeable" type="submit" onclick="makeAbleFields(event)">Change</button>
               </td>
       </tr>
       </form>
   </#list>
  </table>
</div>
</@common.page>