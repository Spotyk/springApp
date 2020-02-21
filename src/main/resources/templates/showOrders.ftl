<#import "parts/common.ftl" as common>

<@common.page>
<div class="flex-container">
  <table class="table">
   <tr class="thead-dark ">
    <th scope="col">Order №</th>
    <th scope="col">Date</th>
    <th scope="col">Status</th>
    <th scope="col">Total Sum</th>
    <th scope="col">Action</th>
   </tr>
   <#if orders??>
   <#list orders as order>
       <tr>
               <td>
               ${order.id}
                    <button onclick="getOrderDetails(event, ${order.id})" class="expand">+</button>
                    <div class="order-details no-display"></div>
               </td>
               <td>
                    ${order.orderDate}
               </td>
               <td>
                    ${order.statusSet[0]}
               </td>
               <td>
                    ${order.totalSum}
               </td>
               <td>
               <form action="/changeOrderStatus" class="change-order-status" method="POST">
                   <input type="hidden" name="_csrf" value="${_csrf.token}" />

                    <input class="order-id" type="hidden" value="${order.id}" name="orderId"/>
                    <select name="orderStatus" class="custom-select status-select" onchange="changeStatus(event)">
                       <option selected>Choose Status</option>
                       <option value="CANCELED">CANCELED</option>
                       <option value="IN_PROGRESS">IN_PROGRESS</option>
                       <option value="DONE">DONE</option>
                       </select>
               </form>
              </td>
       </tr>
       </#list>
       </#if>

  </table>
</div>
</@common.page>
