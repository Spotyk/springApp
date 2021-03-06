<#import "parts/common.ftl" as common>
<#import "/spring.ftl" as spring/>

<@common.page>
    <div class="flex-container">
        <table class="table">
            <tr class="thead-dark ">
                <th scope="col"><@spring.message "order"/> №</th>
                <th scope="col"><@spring.message "date"/></th>
                <th scope="col"><@spring.message "status"/></th>
                <th scope="col"><@spring.message "totalSum"/></th>
                <th scope="col"><@spring.message "action"/></th>
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
                            <#if order.status =="IN_PROGRESS" >
                                <@spring.message "inProgress"/>
                            </#if>
                            <#if order.status =="CANCELED" >
                                <@spring.message "canceled"/>
                            </#if>
                            <#if order.status =="DONE" >
                                <@spring.message "done"/>
                            </#if>


                        </td>
                        <td>
                            ${order.totalSum}
                        </td>
                        <td>
                            <form action="/cancelOrder" class="change-order-status" method="POST">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                                <input class="order-id" type="hidden" value="${order.id}" name="orderId"/>
                                <input type="button" class="btn btn-danger status" onclick="changeStatus(event)"
                                       value="<@spring.message "cancel"/>"/>
                            </form>
                        </td>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</@common.page>
