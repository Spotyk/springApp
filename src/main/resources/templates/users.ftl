<#import "parts/common.ftl" as common>
<#import "/spring.ftl" as spring/>
<@common.page>

<div class="flex-container">
  <table class="table">
   <tr class="thead-dark ">
    <th scope="col"><@spring.message "userName"/></th>
    <th scope="col"><@spring.message "email"/></th>
    <th scope="col"><@spring.message "country"/></th>
    <th scope="col"><@spring.message "state"/></th>
    <th scope="col"><@spring.message "status"/></th>
    <th scope="col"><@spring.message "action"/></th>
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
                  <select class="custom-select form-control status" name="status" disabled>
                      <#if user.active == true>
                        <option value="active" selected><@spring.message "active"/></option>
                      <#else>
                        <option value="disActive" selected><@spring.message "disActive"/></option>
                      </#if>

                      <option value="active"><@spring.message "active"/></option>
                      <option value="disActive"><@spring.message "disActive"/></option>
                  </select>
               </td>
               <td>
                   <button class="btn btn-outline-secondary changeable" type="submit" onclick="makeAbleFields(event)"><@spring.message "change"/></button>
               </td>
       </tr>
       </form>
   </#list>
  </table>
</div>
</@common.page>