# AndroidApp2 - Team Coffee Run (Tim Hortons App)

## Assignment 4 - MWD3B Android Development
A native Android Jetpack Compose application recreating the **Team Coffee Run** multi-member ordering app from iOSApp2.

### Features Implemented:
1. **Swipeable Tab Navigation**: Full-screen horizontal paging between Welcome and 4 team members (Alex, Jordan, Taylor, Casey).
2. **Direct Header Navigation**: Clickable numbered circles (1..4) to jump directly between team members.
3. **Drink Menu & Customization**:
   - 6 Beverages: Coffee (.50), Hot Chocolate (.50), Tea (.50), Latte (.50), French Vanilla (.00), Cappuccino (.50).
   - 4 Sizes with dynamic price multipliers: S (0.8x), M (1.0x), L (1.3x), XL (1.6x).
   - Sugar (0-4) and Milk (0-4) selection pill buttons.
4. **Order Preparation Flow**:
   - 3-second animated countdown timer ().
   - "Order ready!" confirmation.
   - Interactive 5 coffee cup rating component ().
5. **Grouped Order History**:
   - Modal bottom sheet displaying orders chronologically grouped by calendar day (e.g., "Monday, May 28").
   - Shows team member name, drink, rating cups, size, sugar, and milk.
6. **Celebratory Success View**:
   - Fullscreen celebration with coffee cup and animated sparkle indicators upon Casey's final order.
