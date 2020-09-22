import scala.concurrent.{ExecutionContext, Future}

case class Order(id: Int, userId: Int, productId: Int, quantity: Double)
case class Product(id: Int, description: Int, price: Double)
case class User(id: Int, fullname: String, email: String)

def getOrder(id: Int)(implicit ec: ExecutionContext): Future[Order] = ???
def getUser(id: Int)(implicit ec: ExecutionContext): Future[User] = ???
def getProduct(id: Int)
              (implicit ec: ExecutionContext): Future[Product] = ???

case class OrderDetails(order: Order, user: User, product: Product)

def getOrderDetails(orderId: Int)
                   (implicit ec: ExecutionContext): Future[OrderDetails] =
  for {
    order <- getOrder(orderId)
    user <- getUser(order.userId)
    product <- getProduct(order.productId)
  } yield OrderDetails(order, user, product)
