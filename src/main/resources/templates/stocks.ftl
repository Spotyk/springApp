<#import "parts/common.ftl" as common>
<#import "/spring.ftl" as spring/>
<@common.page>

    <div class="flex-container">
        <table class="table">
            <tr class="thead-dark ">
                <th scope="col"><@spring.message "stock"/></th>
                <th scope="col"><@spring.message "quantity"/></th>
            </tr>
            <#list stocks as stock>
                <tr>
                    <form class="form" method="POST" action="/changeUserInfo">
                        <input class="form-control id" type="hidden" name="id" value="${stock.id}">
                        <td>
                            <input type="text" class="form-control username" name="username" value="${stock.name}"
                                   disabled>
                        </td>
                        <td>
                            <input type="text" class="form-control email" name="email" value="${stock.quantity}"
                                   disabled>
                        </td>

                    </form>
                </tr>
            </#list>
        </table>
    </div>
</@common.page>